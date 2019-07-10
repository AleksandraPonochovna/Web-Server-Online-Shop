<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<center>
    <%
        response.getWriter().write("<center>");
        String notFoundUser = (String) request.getAttribute("notfound");
        if (notFoundUser != null) {
            response.getWriter().write(notFoundUser);
        }
        response.getWriter().write("</center>");
    %>
    <h2>Sign In</h2>
    <form action="/users" method="get">
        Email:<br>
        <input type="email" name="email" value="">
        <br>
        Password:<br>
        <input type="password" name="password" value="">
        <br><br>
        <input type="submit" value="Sign In">
    </form>
</center>
</body>
</html>
