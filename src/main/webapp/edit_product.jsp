<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
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
    <h2>Edit Product</h2>
    <form action="/products/edit" method="post">
        Name:<br>
        <input type="text" name="name" value="<%= request.getAttribute("oldName") == null ? ""
        : request.getAttribute("oldName")%>">
        <br>
        Description:<br>
        <input type="text" name="description" value="<%= request.getAttribute("oldDescription") == null ? ""
        : request.getAttribute("oldDescription")%>">
        <br>
        Price:<br>
        <input type="number" step="0.01" name="price" value="<%= request.getAttribute("oldPrice") == null ? ""
        : request.getAttribute("oldPrice")%>">
        <br><br>
        <button type="submit">Edit product</button>
    </form>
</center>
</body>
</html>
