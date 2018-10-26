<%-- 
    Document   : notifications
    Created on : Oct 24, 2018, 2:23:04 PM
    Author     : Mathenge
--%>

<%@page import="java.sql.*"%>
<%@page import="databaseconnect.ConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Notifications</title>
        <link rel="stylesheet" type="text/css" href="css/materialize.min.css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <meta name = "viewport" content = "width = device-width, initial-scale = 1">  
        <!-- <link rel="stylesheet" type="text/css" href="styles.css">     -->
        <link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </head>
    <body>
        <nav class="blue-grey">
            <div class="nav-wrapper">
                <a href="startpage.jsp" class="brand-logo">Admin Module</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="badges.html">About</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <%!
                private Connection conn = null;
                private Statement stmt = null;
                private Statement nstmt = null;
                private ResultSet rs = null;
                private ResultSet nextrs = null;
                ConnectionManager conman = new ConnectionManager();
            %>
            <%
                conn = conman.getConnection();
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

                        String adminsql = "select * from `admins` where `Admin No` = " + String.valueOf(adminno);
                        nstmt = conn.createStatement();
                        nextrs = nstmt.executeQuery(adminsql);
                        String adminname = "";

                        while (nextrs.next()) {
                            adminname = nextrs.getString(2);
                        }

                        out.println("	<tr>\n"
                                + "						<td>" + notno + "</td>\n"
                                + "						<td>" + subject + "</td>\n"
                                + "                                               <td>" + content + "</td>\n"
                                + "                                               <td>" + adminname + "</td>\n"
                                + "                                               <td>  <a class=\"waves-effect waves-light btn modal-trigger\" href=\"#" + notno + "\">Modal</a></td>\n"
                                + "					</tr>\n");
                        out.println("  <div id=\"" + notno + "\" class=\"modal modal-fixed-footer\">"
                                + "<div class=\"modal-content\">"
                                + "<h4>Subject: " + subject + "</h4>"
                                + "<hr>"
                                + "<h5>Written by: " + adminname + "</h5>"
                                + "<img class=\"responsive-img\" src=\""+imagedir+"\">"
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
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.modal').modal();
        });
    </script>
</html>

