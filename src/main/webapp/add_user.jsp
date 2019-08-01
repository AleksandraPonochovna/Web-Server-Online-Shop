<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<center>
    <h2>Add User</h2>
<form action="/admin/add/user" method="post">
    Email:<br>
    <input type="email" name="email" value="${email}">
    <br>
    Password:<br>
    <input type="password" name="password" value="">
    <br>
    Repeat password:<br>
    <input type="password" name="rpassword" value="">
    <br>
    <input type="radio" name="role" value="user">User<br>
    <input type="radio" name="role" value="admin">Admin<br>
    <br><br>
    <c:if test="${passwordsError != null}">
        ${passwordsError}
    </c:if>
    <c:if test="${validFields != null}">
        ${validFields}
    </c:if>
    <br>
    <button>Add user</button>
</form>
</center>
</body>
</html>