/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import broadcast.*;
import databaseconnect.ConnectionManager;
import secure.Bcrypting;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathenge
 */
public class Settings extends HttpServlet {

    private Connection conn = null;
    private Statement stmt = null;
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
            HttpSession session = request.getSession();

            ConnectionManager conman = new ConnectionManager();
            conn = conman.getConnection();
            stmt = conn.createStatement();

            if (request.getParameter("changepass") != null) {
                String newpass = request.getParameter("pwd1");
                Bcrypting changepwd = new Bcrypting();
                String hashednewpass = changepwd.hashPassword(newpass);

                String sql = "UPDATE `admins` SET `Admin Password`= '" + hashednewpass + "' WHERE `Admin Name`= '" + session.getAttribute("adminname") + "'";
                stmt.executeUpdate(sql);
                request.setAttribute("message", "You have successfully changed your password");
                request.getRequestDispatcher("successmodal.jsp").forward(request, response);
            } else if (request.getParameter("changename") != null) {
                String newusername = request.getParameter("username");
                String querysql = "select * from admins where `Admin Name` = '" + newusername + "'";
                rs = stmt.executeQuery(querysql);

                if (rs.next()) {
                    request.setAttribute("warning", "Username already exists");
                    request.getRequestDispatcher("account.jsp#changeusername").forward(request, response);
                } else {
                    String updatesql = "UPDATE `admins` SET `Admin Name`= '" + newusername + "' WHERE `Admin Name`= '" + session.getAttribute("adminname") + "'";
                    stmt.executeUpdate(updatesql);
                    request.removeAttribute("adminname");
                    request.setAttribute("adminname", newusername);
                    request.setAttribute("message", "You have successfully changed your username");
                    request.getRequestDispatcher("successmodal.jsp").forward(request, response);
                }
            } else if (request.getParameter("changedesc") != null) {
                String newdesc = request.getParameter("desc");
                String updatesql = "UPDATE `admins` SET `Description`= '" + newdesc + "' WHERE `Admin Name`= '" + session.getAttribute("adminname") + "'";
                stmt.executeUpdate(updatesql);
                request.setAttribute("message", "You have successfully changed your description");
                request.getRequestDispatcher("successmodal.jsp").forward(request, response);
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
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
