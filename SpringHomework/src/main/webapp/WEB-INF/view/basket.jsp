<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<div style="display: inline-block;">
    <c:if test="${productsInBasket.size()==0}">
        <form action="returnFromBasket" method="post">
            <h1 style="color: red">No products in basket, comeback to Products page and select some.</h1>
            <input type="submit" value="Back to products page">
        </form>
    </c:if>
    <c:forEach items="${productsInBasket}" var="basketProduct">
        <p>Product ID - ${basketProduct.key.id}:</p>
        <p>Product Name - ${basketProduct.key.name}</p>
        <p>Product Calories - ${basketProduct.key.calories}</p>
        <p>Product Price - ${basketProduct.key.price}</p>
        <p>Product Quantity - ${basketProduct.key.quantity}</p>
        <p>Product Categories :</p>
        <c:forEach items="${basketProduct.key.categories}" var="category">
            <p>Category ID - ${category.id}</p>
            <p>Category Name - ${category.name}</p>
        </c:forEach>
        <p>Quantity of products: </p>
        <form action="basket" method="post">
            <label for="quantityProducts"></label>
            <select name="productQuantity_${basketProduct.key.id}" id="quantityProducts" form="orderForm">
                <c:forEach var="i" begin="1" end="10">
                    <c:choose>
                        <c:when test="${i == basketProduct.value}">
                            <option value="${basketProduct.value}" selected="selected">${basketProduct.value}</option>
                        </c:when>
                        <c:when test="${i != basketProduct.value}">
                            <option value="${i}">${i}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="checkbox" id="buy_product" name="selectedProduct" value="${basketProduct.key.id}"
                   checked="checked" form="orderForm">
            <label for="buy_product"> Buy </label><br>
        </form>
        <form action="removeProduct" method="post">
            <input type="hidden" name="removeId" value="${basketProduct.key.id}">
            <input type="submit" value="Delete from basket" style="margin-top: 5px">
        </form>
        <p>----------------------------</p>
    </c:forEach>
</div>
<c:if test="${productsInBasket.size()>0}">
    <form action="startCheckout" method="post" id="orderForm" onclick="checkCheckboxes()">
        <input type="submit" value="Place order">
    </form>
</c:if>
</body>
<script>
    function checkCheckboxes(){
        let checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
        if(checkboxes.length===0){
            event.preventDefault();
        }
    }

</script>
</html>
