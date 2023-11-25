<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div id="formDiv">
    <form:form action="processLogin" method="post" id="loginForm" modelAttribute="authenticationEntity">
        <label for="username">Username:</label>
        <form:input path="username" type="text" id="username" name="username"/>
        <br>
        <form:errors path="username" cssStyle="color: red"/>
        <br>
        <label for="password">Password:</label>
        <form:input path="password" type="text" id="password" name="password"/>
        <br>
        <form:errors path="password" cssStyle="color: red"/>
        <br>
        <input type="submit" id="buttonLog" value="Submit">
    </form:form>
</div>
<%--<script src="<c:url value='/resources/loginScript.js'/>"></script>--%>
</body>
</html>
