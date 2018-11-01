<%-- 
    Document   : checklogin
    Created on : Nov 1, 2018, 2:01:48 PM
    Author     : Mathenge
--%>

<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <%
           String admincheck = (String) session.getAttribute("adminname");
           if (admincheck == null){
               response.sendRedirect("adminlogin.jsp?message=" + URLEncoder.encode("Please log in first", "UTF-8"));
           }
        %>
    </body>
</html>
