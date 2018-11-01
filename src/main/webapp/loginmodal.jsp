<%-- 
    Document   : loginmodal
    Created on : Oct 30, 2018, 11:12:08 AM
    Author     : Mathenge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Please log in</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <div id="successmodal" class="modal">
            <div class="modal-content">
                <h4>Log In First</h4>
                <p>${warning}</p>
            </div>
            <div class="modal-footer">
                <a href="adminlogin.jsp" class="modal-close waves-effect waves-green btn-flat">Agree</a>
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.modal').modal('open');
                // $('.modal').modal('open');
            });

        </script>
    </body>
</html>
