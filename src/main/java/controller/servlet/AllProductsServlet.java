package controller.servlet;

import factory.ProductServiceFactory;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/products")
public class AllProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.getAllProducts());
        request.getRequestDispatcher("/products_for_admin.jsp").forward(request, response);
    }

}
