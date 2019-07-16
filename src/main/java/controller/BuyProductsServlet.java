package controller;

import factory.MailServiceFactory;
import model.Code;
import model.User;
import service.MailService;
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

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Code code = new Code(RandomHelper.get4DigitCode(), user);
        request.getSession().setAttribute("code", code);
        mailService.sendOneTimeCode(code);
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
                || houseNumber.isEmpty() ||  enteredCode.isEmpty()) {
            request.setAttribute("valid", "The fields is valid");
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Code code = (Code) request.getSession().getAttribute("code");
            if (nonNull(user) && user == code.getUser()) {
                if (enteredCode.equals(code.getCode())) {
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
