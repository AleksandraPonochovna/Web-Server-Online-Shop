<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Confirm Order</title>
</head>
<body>
<center>
<h2> Confirm order </h2>
<form action="/products/basket/order/confirm" method="POST">
    Code from Email:<br>
    <input type="text" name="code" value=""> <br>
    <br>
    <c:if test="${wrongCode != null}">
        ${wrongCode}
    </c:if>
    <c:if test="${ok != null}">
        ${ok}
    </c:if>
    <br>
    <button type="submit"> Send </button>
    <button><a href="/sign"> Log Out </a></button>
</form>
</center>
</body>
</html>
