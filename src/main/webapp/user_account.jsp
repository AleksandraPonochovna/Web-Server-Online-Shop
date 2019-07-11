<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Account</title>
</head>
<body>
<center>
    <h3>Hello, ${email}!</h3>
    <h4>You can: </h4>
    <button><a href="/users/edit?email=${email}"> Edit my profile </a></button><br>
    <button><a href="/products_for_user.jsp"> All products </a></button><br>
    <button><a href="/sign"> Log Out </a></button><br>
</center>
</body>
</html>
