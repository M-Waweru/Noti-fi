/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import broadcast.Publisher;
import broadcast.*;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author Mathenge
 */
public class BroadcastNot extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String type = request.getParameter("nottype");
            String subject = request.getParameter("notsubject");
            String content = request.getParameter("notcontent");
            
            out.println(""+subject);

//            Publisher publish;
//            try {
//                publish = new Publisher("admin", "admin");
//                publish.publish(subject, content);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SmackException ex) {
//                Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (XMPPException ex) {
//                Logger.getLogger(BroadcastNot.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
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
