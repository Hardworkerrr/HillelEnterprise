<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div id="formDiv">
    <form action="processLogin" method="post" id="loginForm">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password"><br><br>
        <input type="submit" id="buttonLog" value="Submit">
    </form>
</div>
<script src="<c:url value='/resources/loginScript.js'/>"></script>
</body>
</html>
