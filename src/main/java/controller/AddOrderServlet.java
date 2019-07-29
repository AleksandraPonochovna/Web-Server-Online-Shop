package controller;

import factory.BasketServiceFactory;
import factory.CodeServiceFactory;
import factory.MailServiceFactory;
import factory.OrderServiceFactory;
import model.Basket;
import model.Code;
import model.Order;
import model.User;
import service.BasketService;
import service.CodeService;
import service.MailService;
import service.OrderService;
import util.RandomHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products/basket/order")
public class AddOrderServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getMailService();
    private static final BasketService basketService = BasketServiceFactory.getBasketService();
    private static final CodeService codeService = CodeServiceFactory.getCodeService();
    private static final OrderService orderService = OrderServiceFactory.getOrderService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String numberOfPhone = request.getParameter("phone");
        String streetName = request.getParameter("street");
        String houseNumber = request.getParameter("house");
        if (firstName.isEmpty() || lastName.isEmpty() || numberOfPhone.isEmpty() || streetName.isEmpty()
                || houseNumber.isEmpty()) {
            request.setAttribute("valid", "The fields is valid");
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Code code = new Code(RandomHelper.getFourDigitCode(), user);
            codeService.add(code);
            Optional<Basket> optBasket = basketService.getBasketFor(user);
            if (optBasket.isPresent()) {
                Order order = new Order(firstName, lastName, numberOfPhone, streetName,
                        houseNumber, optBasket.get());
                orderService.addOrder(order);
            }
            mailService.sendOneTimeCode(code, user.getEmail());
            request.getRequestDispatcher("/confirm_order.jsp").forward(request, response);
        }
    }

}
