package controller;

import factory.ProductServiceFactory;
import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/product")
public class AddProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double price = Double.valueOf(request.getParameter("price"));
            Product product = new Product(name, description, price);
            productService.addProduct(product);
            response.sendRedirect("/products");
        } catch (NumberFormatException ex) {
            request.setAttribute("validFields", "It isn't rightly. Enter the correct values.");
            request.getRequestDispatcher("/add_product.jsp").forward(request, response);
        }
    }

}

