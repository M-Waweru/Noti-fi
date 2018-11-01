<%-- 
    Document   : startpage
    Created on : Oct 13, 2018, 6:04:01 PM
    Author     : Mathenge
--%>
<%@ include file="checklogin.jsp" %>
<%@ include file="navbar.jsp" %>
<div class="row">
    <div>
        <p>${welcome}</p>
        <p class="red-text">${param.message}</p>
    </div>
    <div class="container">
        <h3 class="header">Noti-Fi Functions</h3>
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
                    <span class="card-title activator grey-text text-darken-4">Broadcast Notification<i
                            class="material-icons right">more_vert</i></span>
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
                    <span class="card-title activator grey-text text-darken-4">Account Settings<i
                            class="material-icons right">more_vert</i></span>
                    <p><a href="account.jsp">Click here</a></p>
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
                    <span class="card-title activator grey-text text-darken-4">Notifications<i
                            class="material-icons right">more_vert</i></span>
                    <p><a href="notifications.jsp">Click here</a></p>
                    >>>>>>> 42f60b0d8155910bea0e0cf3e2588af0f2c7e2ed
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>
