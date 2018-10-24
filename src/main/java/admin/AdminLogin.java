/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import secure.*;
import databaseconnect.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathenge
 */
public class AdminLogin extends HttpServlet {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

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
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("name");
            String password = request.getParameter("pwd");

            ConnectionManager conman = new ConnectionManager();
            conn = conman.getConnection();
            Hashing checkpwd = new Hashing();

            try {
                String sql = "SELECT * FROM `admins` WHERE `Admin Name`=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String dbpassword = rs.getString(4);
                    String dbsalt = rs.getString(5);

                    if (checkpwd.authenticate(password, dbpassword.getBytes(), dbsalt.getBytes()) == true) {
                        int userno = rs.getInt(1);
                        HttpSession session = request.getSession();

                        synchronized (session) {
                            session.setAttribute("adminname", username);
                            session.setAttribute("adminno", userno);
                        }
                        response.sendRedirect("startpage.jsp");
                    } else {
                        out.println("<p style='color: red;'>Incorrect password, try again</p>");
                        RequestDispatcher rs = request.getRequestDispatcher("adminlogin.html");
                        rs.include(request, response);
                    }
                } else {
                    out.println("<p style='color: red;'>Username and password not found</p>");
                    RequestDispatcher rs = request.getRequestDispatcher("adminlogin.html");
                    rs.include(request, response);
                }
            } catch (SQLException ex) {
                out.println(ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }

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
