package controller.servlet;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/users/edit")
public class EditUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();
    private User user;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("id").isEmpty()) {
            Long id = Long.valueOf(request.getParameter("id"));
            user = userService.getById(id);
        }
        if (!request.getParameter("email").isEmpty()) {
            String email = request.getParameter("email");
            user = userService.getByEmail(email);
        }
        request.setAttribute("oldEmail", user.getEmail());
        request.setAttribute("oldPassword", user.getPassword());
        request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            user.setEmail(email);
            user.setPassword(password);
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } catch (NumberFormatException | NullPointerException ex) {
            request.setAttribute("wrong", "Something is wrong. Try again.");
            request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
        }
    }

}
