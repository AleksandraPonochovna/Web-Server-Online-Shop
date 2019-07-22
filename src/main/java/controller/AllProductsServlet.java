package controller;

import factory.ProductServiceFactory;
import model.User;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/products")
public class AllProductsServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String roleCurrentUser = (String) session.getAttribute("roleCurrentUser");
        request.setAttribute("products", productService.getAllProducts());
        if (roleCurrentUser.equals("admin")) {
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        } else if (roleCurrentUser.equals("user")) {
            request.getRequestDispatcher("/products_for_user.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

}
