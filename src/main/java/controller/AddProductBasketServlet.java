package controller;

import factory.BasketServiceFactory;
import factory.ProductServiceFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products/basket")
public class AddProductBasketServlet extends HttpServlet {

    private static final ProductService productService = ProductServiceFactory.getProductService();
    private static final BasketService basketService = BasketServiceFactory.getBasketService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            Optional<Product> optProduct = productService.getById(id);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (optProduct.isPresent()) {
                Product product = optProduct.get();
                basketService.createBasket(user);
                basketService.addProductInBasket(user.getId(), product);
                request.setAttribute("countProductsInBasket", basketService.getCountProducts(user));
                request.setAttribute("productsInBasket", basketService.getProducts(user));
                request.getRequestDispatcher("/basket.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/basket.jsp").forward(request, response);
        }
    }

}
