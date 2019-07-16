<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<center>
    <h3>All Products</h3>
    <button><a href="/admin/users"> All Users </a> </button>
    <button><a href="/add/product"> Add Product </a></button>
    <table border = 1>
        <tr>
            <th> Name </th>
            <th> Description </th>
            <th> Price </th>
            <th> Edit </th>
            <th> Delete </th>
        </tr>
        <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <c:if test="${product.id != null}">
                <td><button><a href="/products/edit?id=${product.id}"> Edit </a></button></td>
                <td><button><a href="/products/delete?id=${product.id}"> Delete </a></button></td>
            </c:if>
            <c:if test="${product.id == null}">
                <td><button><a href="/products"> Edit </a></button></td>
                <td><button><a href="/products"> Delete </a></button></td>
            </c:if>
        </tr>
        </c:forEach>
    </table>
    <button><a href="/sign"> Log Out </a></button>
</center>
</body>
</html>
