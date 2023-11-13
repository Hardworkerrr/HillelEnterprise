<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="postProcess" method="post">
    <label for="nameReg">Full Name:</label>
    <input type="text" id="nameReg" name="nameReg"><br><br>
    <label for="emailReg">Email:</label>
    <input type="text" id="emailReg" name="emailReg"><br><br>
    <label for="phoneReg">Phone Number:</label>
    <input type="text" id="phoneReg" name="phoneReg"><br><br>
    <label for="usernameReg">Username:</label>
    <input type="text" id="usernameReg" name="usernameReg"><br><br>
    <label for="passwordReg">Password:</label>
    <input type="text" id="passwordReg" name="passwordReg"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
