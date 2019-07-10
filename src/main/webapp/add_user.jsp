<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<center>
    <h2>Add User</h2>
            <%
        String error = (String) request.getAttribute("error");
        String valid = (String) request.getAttribute("valid");
        if (valid != null) {
            response.getWriter().write(valid);
        }
        if (error != null) {
            response.getWriter().write(error);
        }
    %>
<form action="/add/user" method="post">
    Email:<br>
    <input type="email" name="email"
           value="<%= request.getAttribute("email") == null ? " " : request.getAttribute("email")%>">
    <br>
    Password:<br>
    <input type="password" name="password" value="">
    <br>
    Repeat password:<br>
    <input type="password" name="rpassword" value="">
    <br><br>
     <button>Add user</button>
</form>
</center>
</body>
</html>
