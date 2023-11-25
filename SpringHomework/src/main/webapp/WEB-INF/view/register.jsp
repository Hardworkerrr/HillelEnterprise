<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<form:form action="processRegister" method="post" id="registerForm" modelAttribute="authenticationEntity">
    <label for="nameReg">Full Name:</label>
    <form:input path="fullName" type="text" id="nameReg" name="nameReg"/>
    <br>
    <form:errors path="fullName" cssStyle="color: red"/>
    <br>
    <label for="emailReg">Email:</label>
    <form:input path="email" type="text" id="emailReg" name="emailReg"/>
    <br>
    <form:errors path="email" cssStyle="color: red"/>
    <br>
    <label for="phoneReg">Phone Number:</label>
    <form:input path="phoneNumber" type="text" id="phoneReg" name="phoneReg"/>
    <br>
    <form:errors path="phoneNumber" cssStyle="color: red"/>
    <br>
    <label for="usernameReg">Username:</label>
    <form:input path="username" type="text" id="usernameReg" name="usernameReg"/>
    <br>
    <form:errors path="username" cssStyle="color: red"/>
    <br>
    <label for="passwordReg">Password:</label>
    <form:input path="password" type="text" id="passwordReg" name="passwordReg"/>
    <br>
    <form:errors path="password" cssStyle="color: red"/>
    <br>
    <input type="submit" value="Submit" id="buttonReg">
</form:form>
<%--<script src="<c:url value='/resources/registerScript.js'/>"></script>--%>
</body>
</html>
