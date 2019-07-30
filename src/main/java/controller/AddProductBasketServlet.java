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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            User user = (User) request.getSession().getAttribute("user");
            if (optProduct.isPresent()) {
                Optional<Basket> optBasket = basketService.getBasketByUser(user);
                Basket basket;
                Product product = optProduct.get();
                if (optBasket.isPresent()) {
                    basket = optBasket.get();
                    basketService.addProduct(basket, product);
                } else {
                    List<Product> products = new ArrayList<>();
                    products.add(product);
                    basket = new Basket(user, products);
                    basketService.add(basket);
                }
                request.setAttribute("countProductsInBasket", basketService.getCountProducts(basket));
                request.setAttribute("productsInBasket", basketService.getProducts(basket));
                request.getRequestDispatcher("/basket.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/basket.jsp").forward(request, response);
        }
    }

}
