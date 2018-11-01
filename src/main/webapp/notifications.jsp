<%-- 
    Document   : notifications
    Created on : Oct 24, 2018, 2:23:04 PM
    Author     : Mathenge
--%>

<%@page import="databaseconnect.ConnectionManager" %>
<%@page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ include file="checklogin.jsp" %>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h3 class="header">List of Sent Notifications</h3>
    <%!
        private Connection conn = null;
        private Statement stmt = null;
        private Statement nstmt = null;
        private ResultSet rs = null;
        private ResultSet nextrs = null;
        ConnectionManager conman = new ConnectionManager();
    %>
    <%            conn = conman.getConnection();
        try {
            String notifisql = "select * from notifications order by `Notification No` desc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(notifisql);
            out.println("<div class=\"container\">\n"
                    + "			<table>\n"
                    + "				<thead>\n"
                    + "					<tr>\n"
                    + "						<td>No</td>\n"
                    + "						<td>Subject</td>\n"
                    + "                                               <td>Content</td>\n"
                    + "                                               <td>Admin Name</td>\n"
                    + "					</tr>\n"
                    + "				</thead>\n"
                    + "				<tbody>\n");
            while (rs.next()) {
                int notno = rs.getInt(1);
                String subject = rs.getString(2);
                String content = rs.getString(3);
                int adminno = rs.getInt(4);
                String imagedir = rs.getString("Image dir");
                System.out.println("Image dir: ---------------" + imagedir);
                String adminsql = "select * from `admins` where `Admin No` = " + String.valueOf(adminno);
                nstmt = conn.createStatement();
                nextrs = nstmt.executeQuery(adminsql);
                String dbadminname = "";
                while (nextrs.next()) {
                    dbadminname = nextrs.getString(2);
                }
                out.println("	<tr>\n"
                        + "						<td>" + notno + "</td>\n"
                        + "						<td>" + subject + "</td>\n"
                        + "                                               <td>" + content + "</td>\n"
                        + "                                               <td>" + dbadminname + "</td>\n"
                        + "                                               <td>  <a class=\"waves-effect waves-light btn modal-trigger\" href=\"#" + notno + "\">View in full</a></td>\n"
                        + "					</tr>\n");
                out.println("  <div id=\"" + notno + "\" class=\"modal modal-fixed-footer\">"
                        + "<div class=\"modal-content\">"
                        + "<h4>Subject: " + subject + "</h4>"
                        + "<hr>"
                        + "<h5>Written by: " + dbadminname + "</h5>"
                        + "<img class=\"responsive-img\" src=\"" + imagedir + "\">"
                        + "<p>" + content + "</p>"
                        + "</div>"
                        + "<div class=\"modal-footer\">"
                        + "<a href=\"\" class=\"modal-close waves-effect waves-green btn-flat\">Agree</a>"
                        + "</div>"
                        + "</div>");
            }
            out.println("</tbody>\n"
                    + "			</table>\n"
                    + "		</div>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    %>      
</div>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>