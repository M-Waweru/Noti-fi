/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import databaseconnect.ConnectionManager;
import secure.Bcrypting;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
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
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("name");
            String password = request.getParameter("pwd");

            ConnectionManager connectionManager = new ConnectionManager();
            conn = connectionManager.getConnection();
            Bcrypting checkpwd = new Bcrypting();
            try {
                String sql = "SELECT * FROM `admins` WHERE `Admin Name`=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String dbpassword = rs.getString(4);
                    System.out.println("" + dbpassword);

                    if (checkpwd.checkPassword(password, dbpassword)) {
                        int userno = rs.getInt(1);
                        HttpSession session = request.getSession();

                        synchronized (session) {
                            session.setAttribute("adminname", username);
                            session.setAttribute("adminno", userno);
                        }
                        request.setAttribute("welcome", "You have logged in successfully");
                        response.sendRedirect("startpage.jsp");
                    } else {
                        request.setAttribute("warning2", "Incorrect password, try again");
                        RequestDispatcher rs = request.getRequestDispatcher("adminlogin.jsp");
                        rs.forward(request, response);
                    }
                } else {
                    request.setAttribute("warning1", "Username not found, try again");
                    RequestDispatcher rs = request.getRequestDispatcher("adminlogin.jsp");
                    rs.forward(request, response);
                }
            } catch (SQLException ex) {
                out.println(ex);
            } catch (NullPointerException ex) {
                System.out.println("Database is offline");
            } finally {
                out.close();
            }

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
