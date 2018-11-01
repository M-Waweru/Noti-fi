<%-- 
    Document   : navbar
    Created on : Oct 28, 2018, 2:04:50 PM
    Author     : Mathenge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <!-- <link rel="stylesheet" type="text/css" href="styles.css">     -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="css/materialize.min.css">
    <%
            String adminname = null;
            if (session.getAttribute("adminname") != null) {
                adminname = (String) session.getAttribute("adminname");
            }
        %>
    </head>
    <body>
        <ul class="sidenav" id="mobile-menu">
            <li><a href="index.jsp#about">About</a></li>
                <%
                    if (adminname != null) {
                        out.println("<li><a href='account.jsp'>" + adminname + "</a></li>");
                        out.println("<li><a href='LogoutServ'>Logout</a></li>");
                    } else {
                        out.println("<li><a href='adminlogin.jsp'>Login</a></li>");
                    }
                %>
        </ul>
        <nav class="blue darken-3">
            <div class="nav-wrapper">
                <a href="startpage.jsp" class="brand-logo">Admin Module</a>
                <a href="#" data-target="mobile-menu" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="index.jsp #about">About</a></li>
                        <%
                            if (adminname != null) {
                                out.println("<li><a href='account.jsp'>" + adminname + "</a></li>");
                                out.println("<li><a href='LogoutServ'>Logout</a></li>");
                            } else {
                                out.println("<li><a href='adminlogin.jsp'>Login</a></li>");
                            }
                        %>
                </ul>
            </div>
        </nav>
        <script type = "text/javascript" src = "js/jquery-3.1.1.min.js"></script>
        <script src = "js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.sidenav').sidenav();
                $('.modal').modal();
                $('select').formSelect();
            });
        </script>
    </body>
</html>
