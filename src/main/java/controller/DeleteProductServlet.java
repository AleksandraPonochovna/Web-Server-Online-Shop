package controller;

import factory.ProductServiceFactory;
import org.apache.log4j.Logger;
import service.ProductService;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/products/delete")
public class DeleteProductServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();
    private static final Logger logger = Logger.getLogger(DeleteProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            productService.deleteProduct(Long.valueOf(id));
            logger.info("Product { id = " + id + "} is deleted in db." );
        }
        response.sendRedirect("/products");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
