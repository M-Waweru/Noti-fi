<%-- 
    Document   : account
    Created on : Oct 22, 2018, 1:21:35 PM
    Author     : Mathenge
--%>
<%@page import="java.sql.*"%>
<%@page import="databaseconnect.ConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="checklogin.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style> 
            .image_box{
                background-image: url('images/compwithcode.jpg');
                background-attachment: fixed;
                /*background-repeat: no-repeat;*/
                background-size: cover;
                padding: 200px;
                color: white;
            }
            .normalcont{
                padding: 200px;
            }
        </style>
        <%!
            private Connection conn = null;
            private Statement stmt = null;
            private ResultSet rs = null;
            ConnectionManager conman = new ConnectionManager();
            String dbadminname;
            String description;
        %>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <%            conn = conman.getConnection();
            try {
                String adminsql = "select * from admins where `Admin Name` = '" + adminname + "'";
                stmt = conn.createStatement();

                rs = stmt.executeQuery(adminsql);

                while (rs.next()) {
                    int adminno = rs.getInt(1);
                    dbadminname = rs.getString(2);
                    description = rs.getString(3);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        %>
        <div class="image_box center parallax-container">
            <div class="parallax">
            </div>
            <h2>Hello <% out.println(adminname);%></h2>
            <p><% out.println(description);%></p>  
        </div>
        <div class="section white normalcont col s12">
            <div class="col s6">
                <h3 class="header">Settings</h3>
                <p><b>Change your password</b></p>
                <button class="waves-effect waves-light btn modal-trigger red" href="#changepassword">Password</button>
                <br>
                <br>
                <%
                    if (!adminname.equals("Admin")) {
                        out.println("<p><b>Change your username</b></p>"
                                + "<button class='waves-effect waves-light btn modal-trigger green' href = '#changeusername'> Username </button><br><br>");
                    }

                %>
                <p><b>Change your description</b></p>
                <button class="waves-effect waves-light btn modal-trigger blue" href="#changedesc">Description</button>
            </div>
                <%        
                if (adminname.equals("Admin")) {
                System.out.println("fdsfsd");
                    out.println("<div class='col s6 offset-6'>"
                            + "<h4>Admin Settings</h4>"
                            + "<a class='waves-effect waves-light btn modal-trigger orange' href='addadmin.jsp'>Admin List</a>"
                            + "</div>");
                }
            %>

        </div>
        <div id="changepassword" class="modal">
            <div class="modal-content">
                <h4>Change password</h4>
                <form name="changepassform"action="Settings" method="POST" onclick="formvalidate()">
                    <div class="row">
                        <div class="input-field">
                            <label for="pwd1">Enter new password</label>
                            <input type="password" name="pwd1" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field">
                            <label for="pwd2">Enter password again</label>
                            <input type="password" name="pwd2" required>
                        </div>
                    </div>
                    <div id="passwarning" class="hiddendiv">
                        <p>Passwords are not the same</p>
                    </div>
                    <div class="row">
                        <div class="input-field">
                            <button class="btn waves-effect waves-light red" type="submit" name="changepass" value="change">Change password
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="changeusername" class="modal">
            <div class="modal-content">
                <h4>Change username</h4>
                <form name="changenameform"action="Settings" method="POST" onclick="formvalidate()">
                    <div class="row">
                        <div class="input-field">
                            <label for="username">Username</label>
                            <input type="text" name="username" required>
                        </div>
                    </div>
                    <div id="usernamewarning">
                        <p>${warning}</p>
                    </div>
                    <div class="row">
                        <div class="input-field">
                            <button class="btn waves-effect waves-light red" type="submit" name="changename" value="change">Change username
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="changedesc" class="modal">
            <div class="modal-content">
                <h4>Change description</h4>
                <form name="changedescform"action="Settings" method="POST" onclick="formvalidate()">
                    <div class="row">
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
                            <button class="btn waves-effect waves-light red" type="submit" name="changedesc" value="change">Change description
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script>
        $(document).ready({
        function formvalidate() {
        var pwd1 = document.changepassform.pwd1.value;
                var pwd2 = document.changepassform.pwd2.value;
                if (pwd1 === pwd2) {
        $(#passwarning).show();
        }
        }
        }
        );
    </script>
</html>
