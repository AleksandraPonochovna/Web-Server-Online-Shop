<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<center>
    <h2>Edit User</h2>
<form action="/users/edit" method="post">
    Email:<br>
    <input type="email" name="email" value="${oldEmail}">
    <br>
    Password:<br>
    <input type="password" name="password" value="${oldPassword}">
    <br>
    ${wrong}
    <br>
    <button type="submit">Edit user</button>
</form>
</center>
</body>
</html>
