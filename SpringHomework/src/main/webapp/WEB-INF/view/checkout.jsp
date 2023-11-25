<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<html>
<head>
    <title>Checkout</title>
</head>
<body>

<c:set var="overallPrice"/>

<h1>You want to order :</h1>
<div style="display:inline">
    <c:forEach items="${productsToOrder}" var="orderedProduct">
        <div style="display:inline-block; margin-left: 30px">
            <p>Product Name - ${orderedProduct.key.name}</p>
            <p>Product Calories - ${orderedProduct.key.calories}</p>
            <p>Product Price - ${orderedProduct.key.price}</p>
            <p>Product Categories :</p>
            <c:forEach items="${orderedProduct.key.categories}" var="category">
                <p>Category Name - ${category.name}</p>
            </c:forEach>
            <p><b>Buying Quantity: ${orderedProduct.value}</b></p>
        </div>
        <c:set var="overallPrice" value="${overallPrice + (orderedProduct.key.price*orderedProduct.value)}"/>
    </c:forEach>
</div>
<form action="checkout/success" method="post">
    <h1 style="font-size: 30px">Overall price :</h1>
    <p style="font-size: 25px"><b><i>${overallPrice} UAH</i></b></p>
    <p style="font-size: 25px"><b>Select payment :</b></p>
    <input type="radio" id="paymentCard" name="paymentType" value="Credit Card" checked>
    <label for="paymentCard">Credit Card</label><br>
    <input type="radio" id="paymentCheck" name="paymentType" value="Check">
    <label for="paymentCheck">Check</label><br>
    <input type="radio" id="paymentBank" name="paymentType" value="Bank Payment">
    <label for="paymentBank">Bank Payment</label><br>
    <input type="radio" id="paymentCash" name="paymentType" value="Cash">
    <label for="paymentCash">Cash</label><br>
    <div class="row">
        <div>
            <h2>Shipping Address</h2>
            <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
            <input type="text" id="adr" name="address" placeholder="542 W. 15th Street" required="required"
                   pattern="[A-Za-z0-9]{1,20}">
            <label for="city"><i class="fa fa-institution"></i> City</label>
            <input type="text" id="city" name="city" placeholder="New York" required="required"
                   pattern="[A-Za-z0-9]{1,20}">
            <label for="zip"><i class="fa fa-dropbox"></i>Zip</label>
            <input type="text" id="zip" name="zip" placeholder="10001" required="required" pattern="[A-Za-z0-9]{1,20}">
            <label for="country"><i class="fa fa-globe"></i>Country</label>
            <input type="text" id="country" name="country" placeholder="United States" required="required"
                   pattern="[A-Za-z0-9]{1,20}">
        </div>
    </div>
    <input type="submit" value="Submit Order" style="margin-top: 30px;font-size: 25px">
</form>
</body>
</html>
