<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Products</title>
</head>
<body>
<c:forEach items="${requestScope.products}" var="product">
    <p>Product ID - ${product.id}:</p>
    <p>Product Name - ${product.name}</p>
    <p>Product Calories - ${product.calories}</p>
    <p>Product Price - ${product.price}</p>
    <p>Product Quantity - ${product.quantity}</p>
    <p>Product Categories :</p>
    <c:forEach items="${product.categories}" var="category">
        <p>Category ID - ${category.id}</p>
        <p>Category Name - ${category.name}</p>
    </c:forEach>
    <p>----------------------------</p>
</c:forEach>
</body>
</html>
