package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.Product;
import model.User;
import service.ProductService;
import service.UserService;

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

    private static final UserService userService = UserServiceFactory.getUserService();
    private static final ProductService productService = ProductServiceFactory.getProductService();

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
                userService.addProductInBasket(user, product);
                request.setAttribute("countProductsInBasket", user.getBasketSize());
                request.setAttribute("productsInBasket", user.getBasket());
                request.getRequestDispatcher("/basket.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/basket.jsp").forward(request, response);
        }
    }
}
