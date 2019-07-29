package controller;

import factory.CodeServiceFactory;
import factory.OrderServiceFactory;
import model.Code;
import model.Order;
import model.User;
import service.CodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products/basket/order/confirm")
public class ConfirmOrderServlet extends HttpServlet {

    private static final CodeService codeService = CodeServiceFactory.getCodeService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String enteredCode = request.getParameter("code");
        if (enteredCode.isEmpty()) {
            request.setAttribute("wrongCode", "The code is wrong. Try again");
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Optional<Code> optionalCode = codeService.getLastCodeForUser(user);
            if (optionalCode.isPresent()) {
                Code code = optionalCode.get();
                if (enteredCode.equals(code.getCode())) {
                    request.setAttribute("ok", "Your buying was successful");
                    request.getRequestDispatcher("/confirm_order.jsp").forward(request, response);
                } else {
                    request.setAttribute("wrongCode", "The code is wrong. Try again");
                    request.getRequestDispatcher("/confirm_order.jsp").forward(request, response);
                }
            }
        }
    }

}
