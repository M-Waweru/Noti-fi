/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.sun.xml.ws.transport.tcp.util.ConnectionManagementSettings;
import databaseconnect.ConnectionManager;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add administrators</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/materialize.min.css\">");
            out.println("<script type=\"text/javascript\" src=\"js/jquery-3.1.1.min.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"js/materialize.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            ConnectionManager conman = new ConnectionManager();
            conn = conman.getConnection();

            try {
                String adminsql = "select * from admins";
                stmt = conn.createStatement();

                rs = stmt.executeQuery(adminsql);
                out.println("<div class=\"container\">\n" +
"			<table>\n" +
"				<thead>\n" +
"					<tr>\n" +
"						<td>No</td>\n" +
"						<td>Name</td>\n" +
"                                               <td>Description</td>\n" +
"					</tr>\n" +
"				</thead>\n" +
"				<tbody>\n");
                while (rs.next()) {
                    int adminno = rs.getInt(1);
                    String adminname = rs.getString(2);
                    String description = rs.getString(3);
                    
                    out.println("	<tr>\n" +
"						<td>"+adminno+"</td>\n" +
"						<td>"+adminname+"</td>\n" +
"                                               <td>"+description+"</td>\n" +
"					</tr>\n");
                }
                out.println("</tbody>\n" +
"			</table>\n" +
"		</div>");
            } catch (Exception ex) {
                System.out.println("" + ex);
            }
            
            out.println("<br>");
            out.println("<div class=\"container\">");
            out.println("<button class=\"btn waves-effect waves-light\" type=\"submit\" name=\"action\">Submit</button>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
