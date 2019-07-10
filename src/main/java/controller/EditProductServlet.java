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


@WebServlet(value = "/products/edit")
public class EditProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();
    private Product product;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        product = productService.getById(id);
        request.setAttribute("oldName", product.getName());
        request.setAttribute("oldDescription", product.getDescription());
        request.setAttribute("oldPrice", product.getPrice());
        request.getRequestDispatcher("/edit_product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            product.setName(name);
            product.setDescription(description);
            product.setPrice(Double.valueOf(price));
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        } catch (NumberFormatException | NullPointerException ex) {
            request.setAttribute("wrong", "Something is wrong. Try again.");
            request.getRequestDispatcher("/edit_product.jsp").forward(request, response);
        }
    }

}
