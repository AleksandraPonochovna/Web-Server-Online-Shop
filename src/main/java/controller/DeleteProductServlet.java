package controller;

import factory.ProductServiceFactory;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/products/delete")
public class DeleteProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String role = (String) session.getAttribute("role");
        if (role.equals("admin")) {
            String id = request.getParameter("id");
            if (id != null) {
                productService.deleteProduct(Long.valueOf(id));
            }
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/");
        }
    }

}
