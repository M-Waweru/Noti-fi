/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import broadcast.Publisher;
import schedule.*;
import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import notifications.Notification;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

/**
 *
 * @author Mathenge
 */
public class UploadServlet extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(UploadServlet.class.getCanonicalName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        String type = request.getParameter("nottype");
        String subject = request.getParameter("notsubject");
        String content = request.getParameter("notcontent");
        final String path = request.getServletContext().getRealPath("/notimages");
        final Part filePart = request.getPart("notimage");
        final String fileName = getFileName(filePart);
        String imagedir = path + "\\" + fileName;
        String scheduledate = request.getParameter("schnotdate");

        HttpSession session = request.getSession();
        int adminno = (int) session.getAttribute("adminno");

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                    new Object[]{fileName, path});
            writer.println("" + imagedir);

            if (request.getParameter("later") != null) {
                Notification notifi = new Notification(subject, content, adminno, Integer.parseInt(type), imagedir);

                int notifino = notifi.saveNotification();
                if (notifino > 0) {
                    ScheduleCreation schedulenotifi = new ScheduleCreation(notifino, scheduledate);

                    if (schedulenotifi.saveSchedule() == true) {
                        request.setAttribute("message", "You have successfully scheduled this notification");
                        request.getRequestDispatcher("successmodal.jsp").forward(request, response);
                    }
                }

            } else {
                Publisher publish;
                try {
                    publish = new Publisher("admin", "admin");
                    publish.publish(subject, content, imagedir);
                    Notification notifi = new Notification(subject, content, adminno, Integer.parseInt(type), imagedir);
                    request.setAttribute("message", "You have successfully broadcasted this notification");
                    request.getRequestDispatcher("successmodal.jsp").forward(request, response);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SmackException ex) {
                    Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
                } catch (XMPPException ex) {
                    Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
