<%-- 
    Document   : addadmin
    Created on : Oct 9, 2018, 2:16:44 PM
    Author     : Mathenge
--%>

<%@page import="databaseconnect.ConnectionManager" %>
<%@page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin List</title>
        <link rel="stylesheet" type="text/css" href="css/materialize.min.css">
    </head>
    <body>
        <%@ include file="checklogin.jsp" %>
        <%@ include file="navbar.jsp" %>
        <%            if (!adminname.equals("Admin")) {
                response.sendRedirect("startpage.jsp?message=" + URLEncoder.encode("You don't have access to that page", "UTF-8"));
            }
        %>
        <div class="container">
            <h3>Admin Accounts Settings</h3>
            <p><b>Add an admin</b></p>
            <button class="waves-effect waves-light btn modal-trigger red" href="#adduser">Add form</button>        
        </div>
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
                String dbadminname = rs.getString(2);
                String description = rs.getString(3);

                        out.println("	<tr>\n"
                                + "						<td>" + adminno + "</td>\n"
                                + "						<td>" + dbadminname + "</td>\n"
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
            <div id="adduser" class="modal">
                <div class="modal-content">
                    <h4>Add user</h4>
                    <form name="adduserform"action="Settings" method="POST">
                        <div class="row">
                            <div class="input-field">
                                <label for="username">Username</label>
                                <input type="text" name="username" required>
                            </div>
                            <div class="input-field">
                                <label for="desc">Description</label>
                                <input type="text" name="desc" required>
                            </div>
                        </div>
                        <div id="usernamewarning">
                            <p>${warning}</p>
                        </div>
                        <div class="row">
                            <div class="input-field">
                                <button class="btn waves-effect waves-light red" type="submit" name="adduser" value="adduser">Add new user
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
        </div>
    </body>
</html>
