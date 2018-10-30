<%-- 
    Document   : navbar
    Created on : Oct 28, 2018, 2:04:50 PM
    Author     : Mathenge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <meta name = "viewport" content = "width = device-width, initial-scale = 1">  
        <!-- <link rel="stylesheet" type="text/css" href="styles.css">     -->
        <link rel = "stylesheet"
              href = "https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel = "stylesheet"
              href = "css/materialize.min.css">
        <script type = "text/javascript"
        src = "js/jquery-3.1.1.min.js"></script>           
        <script src = "js/materialize.min.js"></script> 
        <%
            String adminname = "";
            if (session.getAttribute("adminname") != null && session.getAttribute("adminno") != null) {
                adminname = (String) session.getAttribute("adminname");
            } else {
//                request.setAttribute("warning", "Session timed out, please login again");
//                request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
            }
        %>
    </head>
    <body>
        <ul class="sidenav" id="mobile-menu">
            <li><a href="#">About</a></li>
                <%
                    if (adminname != null) {
                        out.println("<li><a href='account.jsp'>" + adminname + "</a></li>");
                        out.println("<li><a href='LogoutServ'>Logout</a></li>");
                    } else {
                        out.println("<li><a href='adminlogin.jsp'>Login</a></li>");
                    }
                %>
        </ul>
        <nav class="blue-grey">
            <div class="nav-wrapper">
                <a href="startpage.jsp" class="brand-logo">Admin Module</a>
                <a href="#" data-target="mobile-menu" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="badges.html">About</a></li>
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
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.sidenav').sidenav();
            $('.modal').modal();
            $('select').formSelect();
        });
    </script>
</html>
