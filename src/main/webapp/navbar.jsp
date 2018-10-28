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
        <%
            String adminname = (String) session.getAttribute("adminname");
        %>
    </head>
    <body>
        <nav class="blue-grey">
            <div class="nav-wrapper">
                <a href="startpage.jsp" class="brand-logo">Admin Module</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="badges.html">About</a></li>
                        <%
                            if (adminname != null) {
                                out.println("<li><a href=''>" + adminname + "</a></li>");
                                out.println("<li><a href='LogoutServ'>Logout</a></li>");
                            }
                        %>
                </ul>
            </div>
        </nav>
    </body>
</html>
