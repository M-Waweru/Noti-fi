<%-- 
    Document   : startpage
    Created on : Oct 13, 2018, 6:04:01 PM
    Author     : Mathenge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Notifications Page</title>
        <meta charset="UTF-8">
        <meta name = "viewport" content = "width = device-width, initial-scale = 1">  
        <!-- <link rel="stylesheet" type="text/css" href="styles.css">     -->
        <link rel = "stylesheet"
              href = "https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel = "stylesheet"
              href = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
        <script type = "text/javascript"
        src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>           
        <script src = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>
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
                                out.println("<li><a href=''>Logout</a></li>");
                            }
                        %>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div>
                    <%
                        if (adminname != null) {
                            out.println("<p>You have logged in successfully</p>");
                        }
                    %>
                </div>
                <div class="col s6 small">
                    <div class="card small sticky-action">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="images/broadcast1.png">
                        </div>
                        <div class="card-reveal">
                            <span class="card-title grey-text text-darken-4"><i class="material-icons right">close</i></span>
                            <p>A form to enter details about the notification and broadcast</p>
                        </div>
                        <div class="card-action">
                            <span class="card-title activator grey-text text-darken-4">Broadcast Notification<i class="material-icons right">more_vert</i></span>
                            <p><a href="broadcastnot.jsp">Broadcast</a></p>
                        </div>
                    </div>
                </div>
                <div class="col s6 small">
                    <div class="card small sticky-action">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="images/broadcast1.png">
                        </div>
                        <div class="card-reveal">
                            <span class="card-title grey-text text-darken-4"><i class="material-icons right">close</i></span>
                            <p>Edit the administrator account</p>
                        </div>
                        <div class="card-action">
                            <span class="card-title activator grey-text text-darken-4">Account Settings<i class="material-icons right">more_vert</i></span>
                            <p><a href="addadmin.jsp">Click here</a></p>
                        </div>
                    </div>
                </div>
                <div class="col s6 small">
                    <div class="card small sticky-action">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="images/broadcast1.png">
                        </div>
                        <div class="card-reveal">
                            <span class="card-title grey-text text-darken-4"><i class="material-icons right">close</i></span>
                            <p>View and edit notifications</p>
                        </div>
                        <div class="card-action">
                            <span class="card-title activator grey-text text-darken-4">Notifications<i class="material-icons right">more_vert</i></span>
                            <p><a href="broadcastnot.jsp">Click here</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
