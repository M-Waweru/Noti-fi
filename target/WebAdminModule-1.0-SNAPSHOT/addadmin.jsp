<%-- 
    Document   : addadmin
    Created on : Oct 9, 2018, 2:16:44 PM
    Author     : Mathenge
--%>

<%@page import="java.sql.*"%>
<%@page import="databaseconnect.ConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add admin</title>
        <link rel="stylesheet" type="text/css" href="css/materialize.min.css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
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
                private ResultSet rs = null;
                ConnectionManager conman = new ConnectionManager();
            %>
            <%
                conn = conman.getConnection();
                try {
                    String adminsql = "select * from admins";
                    stmt = conn.createStatement();

                    rs = stmt.executeQuery(adminsql);
                    out.println("<div class=\"container\">\n"
                            + "			<table>\n"
                            + "				<thead>\n"
                            + "					<tr>\n"
                            + "						<td>No</td>\n"
                            + "						<td>Name</td>\n"
                            + "                                               <td>Description</td>\n"
                            + "					</tr>\n"
                            + "				</thead>\n"
                            + "				<tbody>\n");
                    while (rs.next()) {
                        int adminno = rs.getInt(1);
                        String adminname = rs.getString(2);
                        String description = rs.getString(3);

                        out.println("	<tr>\n"
                                + "						<td>" + adminno + "</td>\n"
                                + "						<td>" + adminname + "</td>\n"
                                + "                                               <td>" + description + "</td>\n"
                                + "					</tr>\n");
                    }
                    out.println("</tbody>\n"
                            + "			</table>\n"
                            + "		</div>");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            %>
            <br>
            <div class="container">
                <button class="btn waves-effect waves-light" type="submit" name="action">Submit</button>
            </div>   
    </body>
</html>
