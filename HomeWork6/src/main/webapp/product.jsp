<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Products</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<form action="basket" method="post" id="basketForm">
    <button style="font-size:24px;float: right;"><i class="fa fa-shopping-basket"></i></button>
</form>
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
    <form action="product" method="post">
        <label for="quantityProducts"></label>
        <select name="quantity" id="quantityProducts">
            <c:forEach var="i" begin="1" end="10">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>
        <input type="hidden" id="productId" name="productId" value="${product.id}">
        <input type="submit" value="Add to basket">
    </form>
    <p>----------------------------</p>
</c:forEach>
</body>
</html>
