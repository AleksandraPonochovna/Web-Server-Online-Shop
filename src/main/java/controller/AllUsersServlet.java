package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.nonNull;

@WebServlet(value = "/admin/users")
public class AllUsersServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (nonNull(user) && user.getRole().equals("admin")) {
            List<User> allUsers = userService.getAllUsers();
            request.setAttribute("users", allUsers);
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

}
