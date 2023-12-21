<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Products</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
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
</body>

</html>

