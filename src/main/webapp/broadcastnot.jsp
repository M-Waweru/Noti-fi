<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Create Notification</title>
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
        <%!
            
        %>
    </head>
    <body>
        <div class="container">
            <form method="POST" action="BroadcastNot" class="col s12">
                <div class="row">
                    <h3>Broadcast a Notification</h3>
                    <p>Enter notification details below</p>
                </div>
                <div class="row">
                    <div class="input-field col s8">
                        <i class="material-icons prefix">keyboard</i>
                        <input name="notsubject" type="text">
                        <label for="notsubject">Notification Subject</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s8">
                        <i class="material-icons prefix">mode_edit</i>
                        <textarea name="nottype" class="materialize-textarea"></textarea>
                        <label for="nottype">Notification Type</label>
                    </div>
                </div>                
                <div class="row">
                    <div class="input-field col s8">
                        <i class="material-icons prefix">mode_edit</i>
                        <textarea name="notcontent" class="materialize-textarea"></textarea>
                        <label for="notcontent">Notification Content</label>
                    </div>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                    <i class="material-icons right">send</i>
                </button>
            </form>
        </div>
    </body>
</html>
