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
import java.util.Optional;

@WebServlet(value = "/products/edit")
public class EditProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Optional<Product> optProduct = productService.getById(id);
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            request.setAttribute("oldName", product.getName());
            request.setAttribute("oldDescription", product.getDescription());
            request.setAttribute("oldPrice", product.getPrice());
            request.getRequestDispatcher("/edit_product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            Optional<Product> optProduct = productService.getById(id);
            if (optProduct.isPresent()) {
                Product product = optProduct.get();
                product.setName(name);
                product.setDescription(description);
                product.setPrice(Double.valueOf(price));
                request.getRequestDispatcher("/products.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("wrong", "Something is wrong. Try again.");
            request.getRequestDispatcher("/edit_product.jsp").forward(request, response);
        }
    }

}
