<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<%
    response.getWriter().write("<center>");
    String wrongFields = (String) request.getAttribute("wrong");
    if (wrongFields != null) {
        response.getWriter().write(wrongFields);
    }
    response.getWriter().write("</center>");
%>
<center>
    <h2>Edit User</h2>
<form action="/users/edit" method="post">
    Email:<br>
    <input type="email" name="email" value="<%= request.getAttribute("oldEmail") == null ? ""
        : request.getAttribute("oldEmail")%>">
    <br>
    Password:<br>
    <input type="password" name="password" value="<%= request.getAttribute("oldPassword") == null ? ""
        : request.getAttribute("oldPassword")%>">
    <br><br>
    <button type="submit">Edit user</button>
</form>
</center>
</body>
</html>
