<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="databaseconnect.ConnectionManager" %>
<%@page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ include file="navbar.jsp" %>
<%!
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    ConnectionManager conman = new ConnectionManager();
%>
<div class="container">
    <form method="POST" action="UploadServlet" class="col s12" enctype="multipart/form-data">
        <div class="row">
            <h3>Broadcast a Notification</h3>
            <p>Enter notification details below</p>
        </div>
        <div class="row">
            <div class="input-field col s8">
                <i class="material-icons prefix">keyboard</i>
                <input name="notsubject" type="text" required>
                <label for="notsubject">Notification Subject</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s8">
                <i class="material-icons prefix">class</i>
                <select name="nottype" required>
                    <option value="" disabled selected>Choose your option</option>
                    <% conn = conman.getConnection();
                        try {
                            String adminsql = "select * from notification_type";
                            stmt = conn.createStatement();

                            rs = stmt.executeQuery(adminsql);
                            while (rs.next()) {
                                int typeno = rs.getInt(1);
                                String typename = rs.getString(2);
                                String description = rs.getString(3);

                                out.println("<option value='" + typeno + "'>" + typename + "</option>");
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
                <textarea name="notcontent" class="materialize-textarea" required></textarea>
                <label for="notcontent">Notification Content</label>
            </div>
        </div>
        <div class="row">
            <div class="file-field input-field col s8">
                <div class="btn">
                    <span>Image</span>
                    <input name="notimage" type="file" accept="image/*">
                </div>
                <div class="file-path-wrapper col s8">
                    <input class="file-path validate" type="text">
                </div>
            </div>
        </div>
        <div class="row">
            <p class="red-text">${warning}</p>
        </div>
        <div class="row">
            <i class="material-icons prefix">mode_edit</i>
            <div class="input-field col s4">
                <input name="schnotdate" type="text" class="datepicker">
                <label for="schnotdate">When do you want to send this?</label>
            </div>
            <div class="input-field col s4">
                <input name="schnottime" type="text" class="timepicker">
                <label for="schnottime">At what time?</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light" type="submit" name="now" value="Broadcast Now">Broadcast Now
                <i class="material-icons right">send</i>
            </button>
            <button class="btn waves-effect waves-light" type="submit" name="later" value="Schedule">Schedule
                Notification
                <i class="material-icons right">alarm</i>
            </button>
        </div>
    </form>
</div>
<br>
<br>
<br>
<script type="text/javascript" src="js/datepicker.js"></script>
</body>
</html>
