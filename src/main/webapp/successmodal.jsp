<%-- 
    Document   : successmodal
    Created on : Oct 25, 2018, 2:01:05 PM
    Author     : Mathenge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Successful</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </head>
    <body>
        <div id="successmodal" class="modal">
            <div class="modal-content">
                <h4>Successful</h4>
                <p>${message}</p>
            </div>
            <div class="modal-footer">
                <a href="startpage.jsp" class="modal-close waves-effect waves-green btn-flat">Agree</a>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.modal').modal();
            $('.modal').modal('open');
        });

    </script>
</html>
