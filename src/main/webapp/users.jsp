<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>\n<h3>All Users</h3>");
        printWriter.write("<button><a href=\"/add/user\"> Registration </a> </button>");
        printWriter.write("<button><a href=\"/products\"> All Products </a> </button>");
        printWriter.write("<table border = 1>" +
                "<tr>" +
                  "<th> Email </th>" +
                  "<th> Password </th>" +
                  "<th> Edit </th>" +
                  "<th> Delete </th>" +
                "</tr>");
        if (!users.isEmpty()) {
            for (User user : users) {
                request.setAttribute("id", user.getId());
                printWriter.write("<tr>");
                printWriter.write("<td>" + user.getEmail() + "</td>");
                printWriter.write("<td>" + user.getPassword() + "</td>");
                if (user.getId() != null) {
                    printWriter.write("<td><button><a href=\"/users/edit?id=" +
                            user.getId() + "\"> Edit </a></button></td>");
                    printWriter.write("<td><button><a href=\"/users/delete?id=" +
                            user.getId() + "\"> Delete </a></button></td>");
                } else {
                    printWriter.write("<td><button><a href=\"/users" + "\"> Edit </a></button></td>");
                    printWriter.write("<td><button><a href=\"/users" + "\"> Delete </a></button></td>");
                }
                printWriter.write("</tr>");
            }
        }
        printWriter.write("</table>");
        printWriter.write("</center>");
    %>
</body>
</html>
