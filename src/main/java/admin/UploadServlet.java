/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import broadcast.Publisher;
import notifications.Notification;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import schedule.ScheduleCreation;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

/*
  @author Mathenge
 */
public class UploadServlet extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(UploadServlet.class.getCanonicalName());

    private String xmppServerUsername = null;
    private String xmppServerPassword = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        setXmppServerCredentials();

        // Create path components to save the file
        String type = request.getParameter("nottype");
        String subject = request.getParameter("notsubject");
        String content = request.getParameter("notcontent");
        final String path = AdminUtils.getImagesFolderPath();
        final Part filePart = request.getPart("notimage");
        final String fileName = AdminUtils.getFileName(filePart);
        String imagedir = path + File.separator + fileName;

        String scheduledate = request.getParameter("schnotdate");
        String scheduletime = request.getParameter("schnottime");

        HttpSession session = request.getSession();

        int adminno = (int) session.getAttribute("adminno");

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            File file = new File(path + File.separator + fileName);
            out = new FileOutputStream(file);
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
            publishMessage(request, response, subject, content, type, adminno, imagedir, scheduledate, scheduletime);

        } catch (FileNotFoundException e) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + e.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{e.getMessage()});
            publishMessage(request, response, subject, content, type, adminno, null, scheduledate, scheduletime);
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

    private void publishMessage(HttpServletRequest request, HttpServletResponse response, String subject, String content, String type, int adminno,
                                String imagedir, String scheduledate, String scheduletime) {
        Publisher publisher;
        if (request.getParameter("later") != null) {
            long timeDelay = AdminUtils.getDateFromRequest(scheduledate, scheduletime);

            Notification notifi = new Notification(subject, content, adminno, Integer.parseInt(type), imagedir);
            int notifino = notifi.saveNotification();

            try {
                publisher = new Publisher(xmppServerUsername, xmppServerPassword);
                publisher.schedulePublish(subject, content, imagedir, timeDelay);
            } catch (InterruptedException | SmackException | XMPPException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (notifino > 0) {
                ScheduleCreation schedulenotifi = new ScheduleCreation(notifino, scheduledate);

                if (schedulenotifi.saveSchedule()) {
                    try {
                        request.setAttribute("message", "You have successfully scheduled this notification");
                        request.getRequestDispatcher("successmodal.jsp").forward(request, response);
                    } catch (ServletException | IOException ex) {
                        Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else {
            try {
                publisher = new Publisher(xmppServerUsername, xmppServerPassword);
                publisher.publish(subject, content, imagedir);
                Notification notifi = new Notification(subject, content, adminno, Integer.parseInt(type), imagedir);
                notifi.saveNotification();
                request.setAttribute("message", "You have successfully sent this notification to Openfire for broadcast");
                request.getRequestDispatcher("successmodal.jsp").forward(request, response);
            } catch (InterruptedException | SmackException | XMPPException ex) {
                Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ServletException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setXmppServerCredentials() {
        Properties prop = new Properties();
        String propFileName = "config" + File.separator + "xmppserver.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(propFileName)) {
            prop.load(resourceStream);
            xmppServerUsername = prop.getProperty("username");
            xmppServerPassword = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
