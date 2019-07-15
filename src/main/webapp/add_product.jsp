<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<center>
<h2>Add Product</h2>
<form action="/add/product" method="POST">
    Name:<br>
    <input type="text" name="name" value="">
    <br>
    Description:<br>
    <input type="text" name="description" value="">
    <br>
    Price:<br>
    <input name="price" type="number" step="0.01"/> <br>
    <br>
    <c:if test="${validFields != null}">
        ${validFields}
    </c:if>
    <br>
    <button type="submit">Add Product</button>
</form>
</center>
</body>
</html>
