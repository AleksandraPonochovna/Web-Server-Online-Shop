<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<%
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>\n<h3>All Products</h3>");
    printWriter.write("<button><a href=\"/add/product\"> Add Product </a> </button>");
    printWriter.write("<button><a href=\"/users\"> All Users </a> </button>");
    printWriter.write("<table border = 1>" +
            "<tr>" +
            "<th> Name </th>" +
            "<th> Description </th>" +
            "<th> Price </th>" +
            "<th> Edit </th>" +
            "<th> Delete </th>" +
            "</tr>");
    List<Product> products = (List<Product>) request.getAttribute("products");
    if (!products.isEmpty()) {
        for (Product product : products) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + product.getName() + "</td>");
            printWriter.write("<td>" + product.getDescription() + "</td>");
            printWriter.write("<td>" + product.getPrice() + "</td>");
            if (product.getId() != null) {
                printWriter.write("<td><button><a href=\"/products/edit?id=" +
                        product.getId() + "\"> Edit </a></button></td>");
                printWriter.write("<td><button><a href=\"/products/delete?id=" +
                        product.getId() + "\"> Delete </a></button></td>");
            } else {
                printWriter.write("<td><button><a href=\"/products" + "\"> Edit </a></button></td>");
                printWriter.write("<td><button><a href=\"/products" + "\"> Delete </a></button></td>");
            }
            printWriter.write("</tr>");
        }
    }
    printWriter.write("</table>");
    printWriter.write("</center>");
%>
</body>
</html>
