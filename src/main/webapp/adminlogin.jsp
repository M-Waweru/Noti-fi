<%-- 
    Document   : adminlogin
    Created on : Oct 24, 2018, 2:29:53 PM
    Author     : Mathenge
--%>

        <%@ include file="navbar.jsp" %>
        <br>
        <div class="container">
            <form class="col s12" method="POST" action="AdminLogin">
                <div class="row">
                    <div class="col s12">
                        <h3>Authorised Access Only</h3>
                        <h4>Enter login details below</h4>
                    </div>
                </div>
                <div class="row">
                    <p class="red-text">${param.warning}</p>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <label for="name">Admin Name</label>
                        <input placeholder="Enter Admin Name" type="text" name="name" required>
                    </div>
                </div>
                <div class="row">
                    <p class="red-text">${warning1}</p>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <label for="pwd">Password</label>
                        <input placeholder="Enter Password" type="password" name="pwd" required>
                    </div>
                </div>
                <div class="row">
                    <p class="red-text">${warning2}</p>                
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <button class="btn waves-effect waves-light red" type="submit" name="Login">Log In
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>		
            </form>
        </div>
    </body>
</html>
