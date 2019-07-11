package controller;

import factory.ProductServiceFactory;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;
import util.IdGeneratorUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/product")
public class AddProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();
    private static final Logger logger = Logger.getLogger(AddProductServlet.class);
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
            productService.addProduct(IdGeneratorUtil.getProductId(), name, description, price);
            logger.info("Product {" + name + " " + price + "} is added in db.");
            response.sendRedirect("/products");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException ex) {
            request.setAttribute("valid", "It isn't rightly. Enter the correct values.");
            request.getRequestDispatcher("/add_product.jsp").forward(request, response);
        }
    }

}

