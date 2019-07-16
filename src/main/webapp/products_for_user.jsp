<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<center>
    <h3>All Products</h3>
    <button><a href="/products/basket"> Basket </a></button>
    <table border = 1>
        <tr>
            <th> Name </th>
            <th> Description </th>
            <th> Price </th>
            <th> Buy </th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <c:if test="${product.id != null}">
                    <td><button><a href="/products/basket?id=${product.id}"> Buy </a></button></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
