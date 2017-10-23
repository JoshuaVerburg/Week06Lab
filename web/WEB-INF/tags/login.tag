<%-- 
    Document   : login
    Created on : Oct 16, 2017, 9:35:32 AM
    Author     : 727334
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>
<%@attribute name="url" required="false"%>
<%-- any content can be specified here e.g.: --%>
        <div>
            <form action="login" method="post">
                Username: <input type="text" name="username" value=${cookie.username.value}><br>
                Password: <input type="password" name="password" ><br>
                <input type="checkbox" name="remember" >Remember Me<br>
                <input type="submit">
            </form>
        </div>