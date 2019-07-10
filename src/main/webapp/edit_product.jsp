<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<center>
    <h2>Edit Product</h2>
    <form action="/products/edit" method="post">
        Name:<br>
        <input type="text" name="name" value="value="${oldName}">
        <br>
        Description:<br>
        <input type="text" name="description" value="${oldDescription}">
        <br>
        Price:<br>
        <input type="number" step="0.01" name="price" value="${oldPrice}">
        <br>
        ${wrong}
        <br>
        <button type="submit">Edit product</button>
    </form>
</center>
</body>
</html>