<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<center>
<h3>All Users</h3>
<button><a href="/add/user"> Add user </a></button>
<button><a href="/products"> All Products</a> </button>
<table border = 1>
    <tr>
        <th> Email </th>
        <th> Password </th>
        <th> Edit </th>
        <th> Delete </th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <c:if test="${user.id != null}">
                <td><button><a href="/users/edit?id=${user.id}"> Edit </a></button></td>
                <td><button><a href="/users/delete?id=${user.id}"> Delete </a></button></td>
            </c:if>
            <c:if test="${user.id == null}">
                <td><button><a href="/users"> Edit </a></button></td>
                <td><button><a href="/users"> Delete </a></button></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</center>
</body>
</html>
