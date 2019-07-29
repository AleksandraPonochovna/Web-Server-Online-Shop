<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<center>
    <h2>Edit User</h2>
<form action="/admin/users/update?id=${param["id"]}" method="post">
    Email:<br>
    <input type="email" name="email" value=" <c:if test="${oldEmail != null}">${oldEmail}</c:if> ">
    <br>
    Password:<br>
    <input type="password" name="password" value=" <c:if test="${oldPassword != null}">${oldPassword}</c:if> ">
    <br>
    <input type="radio" name="role" value="user">User<br>
    <input type="radio" name="role" value="admin">Admin<br>
    <br>
    <c:if test="${validValues != null}">
        ${validValues}
    </c:if>
    <br>
    <input type="submit" value="Edit user">
</form>
</center>
</body>
</html>
