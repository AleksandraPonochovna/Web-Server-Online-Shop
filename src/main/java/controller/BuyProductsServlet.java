package controller;

import factory.BasketServiceFactory;
import factory.MailServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.Order;
import model.User;
import service.BasketService;
import service.MailService;
import service.OrderService;
import util.IdGeneratorUtil;
import util.RandomHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/products/basket/buy")
public class BuyProductsServlet extends HttpServlet {

    private static final MailService mailService = MailServiceFactory.getMailService();
    private static final OrderService orderService = OrderServiceFactory.getOrdertService();
    private static final BasketService basketService = BasketServiceFactory.getBasketService();


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
        String enteredCode = request.getParameter("code");
        if (firstName.isEmpty() || lastName.isEmpty() || numberOfPhone.isEmpty() || streetName.isEmpty()
                || houseNumber.isEmpty() || enteredCode.isEmpty()) {
            request.setAttribute("valid", "The fields is valid");
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            if (nonNull(user)) {
                Code code = new Code(RandomHelper.getFourDigitCode());
                Order order = new Order(IdGeneratorUtil.getOrderId(), firstName, lastName, numberOfPhone, streetName,
                        houseNumber, enteredCode, basketService.get(user.getId()), user, code);
                mailService.sendOneTimeCode(code);
                basketService.createBasket(user);
                if (user == order.getUser()) {
                    if (enteredCode.equals(code.getCode())) {
                        orderService.addOrder(order);
                        request.setAttribute("ok", "Your buying was successful");
                        request.getRequestDispatcher("/order.jsp").forward(request, response);
                    } else {
                        request.setAttribute("wrongCode", "The code is wrong. Try again");
                        request.getRequestDispatcher("/order.jsp").forward(request, response);
                    }
                }
            }
        }
    }

}

