<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="java.sql.*"%>
<%@page import="databaseconnect.ConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Create Notification</title>
        <meta name = "viewport" content = "width = device-width, initial-scale = 1">  
        <!-- <link rel="stylesheet" type="text/css" href="styles.css">     -->
        <link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel = "stylesheet" href = "css/materialize.min.css">
        <script type = "text/javascript" src = "js/jquery-3.1.1.min.js"></script>           
        <script type = "text/javascript" src = "js/materialize.min.js"></script> 
        <%!
            private Connection conn = null;
            private Statement stmt = null;
            private ResultSet rs = null;
            ConnectionManager conman = new ConnectionManager();
        %>
    </head>
    <body>
    <div class="container">
            <form method="POST" action="UploadServlet" class="col s12" enctype = "multipart/form-data">
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
                        <i class="material-icons prefix">class</i>
                        <select name="nottype">
                            <option value="" disabled selected>Choose your option</option>
                            <%
                                conn = conman.getConnection();
                                try {
                                    String adminsql = "select * from notification_type";
                                    stmt = conn.createStatement();

                                    rs = stmt.executeQuery(adminsql);
                                    while (rs.next()) {
                                        int typeno = rs.getInt(1);
                                        String typename = rs.getString(2);
                                        String description = rs.getString(3);

                                        out.println("<option value='" + typename + "'>" + typename + "</option>");
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            %>
                        </select>
                        <label>Notification Type</label>
                    </div>
                </div>                
                <div class="row">
                    <div class="input-field col s8">
                        <i class="material-icons prefix">mode_edit</i>
                        <textarea name="notcontent" class="materialize-textarea"></textarea>
                        <label for="notcontent">Notification Content</label>
                    </div>
                </div>
                <div class="file-field input-field col s8">
                    <div class="btn">
                        <span>Image</span>
                        <input name="notimage" type="file">
                    </div>
                    <div class="file-path-wrapper col s8">
                        <input class="file-path validate" type="text">
                    </div>
                </div>
                <div class="row">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $('select').formSelect();
        });
    </script>
</html>
