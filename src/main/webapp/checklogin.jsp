<%-- 
    Document   : checklogin
    Created on : Nov 1, 2018, 2:01:48 PM
    Author     : Mathenge
--%>

<%@page import="java.net.URLEncoder" %>

<%
    String admincheck = (String) session.getAttribute("adminname");
    if (admincheck == null) {
        response.sendRedirect("adminlogin.jsp?message=" + URLEncoder.encode("Please log in first", "UTF-8"));
    }
%>
