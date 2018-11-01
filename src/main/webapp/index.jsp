<%-- 
    Document   : index
    Created on : Oct 28, 2018, 8:18:32 PM
    Author     : Mathenge
--%>
<%@ include file="navbar.jsp" %>
<style>
    .image_box {
        background-image: url('images/phoneinhand.jpg');
        background-attachment: fixed;
        /*background-repeat: no-repeat;*/
        background-size: cover;
        padding: 200px;
        color: white;
    }

    .normalcont {
        padding: 200px;
    }

    footer {
        background-color: black;
        color: white;
    }
</style>
<div class="image_box center parallax-container">
    <div class="parallax">
    </div>
    <h2>This is Noti-fi</h2>
    <p>The LAN Message Broadcasting System</p>
</div>
<div id="about" class="section white normalcont">
    <div class="row container">
        <h2 class="header">About</h2>
        <p class="grey-text text-darken-3 grey lighten-5">Noti-fi is a Local Area Network broadcasting system that sends
            messages and notifications to users connected to the WiFi.</p>
        <p>Using XMPP, hosted by the Openfire Server from Ignite Realtime, the system broadcasts important messages to
            users on a network. These messages could be used to notify interested parties
            events taking place or an importance message from the administrator.</p>
        <p>Our scope of work is focused on universities that want to send urgent messages to students at an affordable
            price. It will allow the school to use existing infrastructure such as routers,
            servers and other networking devices for the delivery of messages in scalable fashion.</p>
    </div>
</div>
<footer>
    <p>Project by Mathenge and John</p>
    <p>Wallpapers used</p>
    <a href='https://www.freepik.com/free-vector/modern-business-background_3190141.htm'>Designed by BiZkettE1</a>
</footer>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('.parallax').parallax();
    });
</script>
</body>
</html>
