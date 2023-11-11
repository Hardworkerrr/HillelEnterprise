<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>

<c:forEach items="${requestScope.customers}" var="customer">
    <p>User ID - ${customer.id}:</p>
    <p>User Name - ${customer.name}</p>
    <p>User Phone - ${customer.phoneNumber}</p>
    <p>User Country - ${customer.address.country}</p>
    <p>User City - ${customer.address.city}</p>
    <p>User Street - ${customer.address.street}</p>
    <p>----------------------------</p>
</c:forEach>
</body>


</html>
