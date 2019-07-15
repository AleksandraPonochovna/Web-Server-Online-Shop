<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<center>
    <h2>Sign In</h2>
    <form action="/sign" method="post">
        Email:<br>
        <input type="email" name="email" value="">
        <br>
        Password:<br>
        <input type="password" name="password" value="">
        <br>
        <c:if test="${unknown != null}">
            ${unknown}
        </c:if>
        <br>
        <input type="submit" value="Sign In">
    </form>
</center>
</body>
</html>
