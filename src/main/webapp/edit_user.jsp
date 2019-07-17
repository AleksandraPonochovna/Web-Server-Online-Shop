<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<center>
    <h2>Edit User</h2>
<form action="/admin/users/edit" method="post">
    Email:<br>
    <input type="email" name="email" value=" <c:if test="${oldEmail != null}"><br>${oldEmail}<br> </c:if> ">
    <br>
    Password:<br>
    <input type="password" name="password" value=" <c:if test="${oldPassword != null}"><br>${oldPassword}<br> </c:if> ">
    <br>
    <c:if test="${validValues != null}">
        ${validValues}
    </c:if>
    <br>
    <button type="submit">Edit user</button>
</form>
</center>
</body>
</html>
