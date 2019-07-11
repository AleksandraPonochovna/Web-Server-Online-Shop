<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<center>
    <h3>All Products</h3>
    <table border = 1>
        <tr>
            <th> Name </th>
            <th> Description </th>
            <th> Price </th>
        </tr>
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
            </tr>
    </table>
    <button><a href="/user_account.jsp"> Back to profile </a></button>
</center>
</body>
</html>