<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sait" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <sait:login/>

        ${errorMessage}
        ${logoutMessage}
    </body>
</html>
