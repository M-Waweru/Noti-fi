<%-- 
    Document   : index
    Created on : Oct 28, 2018, 8:18:32 PM
    Author     : Mathenge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Module</title>
        <meta name = "viewport" content = "width = device-width, initial-scale = 1">  
        <!-- <link rel="stylesheet" type="text/css" href="styles.css">     -->
        <link rel = "stylesheet"
              href = "https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel = "stylesheet"
              href = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
        <script type = "text/javascript"
        src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>           
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script> 
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <nav class="blue-grey">
            <div class="nav-wrapper">
                <a href="#" class="brand-logo">Noti-Fi</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="adminlogin.jsp">Login</a></li>
                </ul>
            </div>
        </nav>
    </body>
</html>
