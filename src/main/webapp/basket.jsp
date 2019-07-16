<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Basket</title>
</head>
<body>
<center>
    <h3>Basket</h3>
    <button><a href="/products"> All Products </a></button>
    <table border = 1>
        <tr>
            <th> Name </th>
            <th> Description </th>
            <th> Price </th>
        </tr>
        <c:forEach var="product" items="${productsInBasket}">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
    </table>
    <button><a href="/products/basket/buy"> Buy </a></button>
</center>
</body>
</html>

