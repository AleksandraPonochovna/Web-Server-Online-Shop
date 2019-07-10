package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/sign")
public class SignInServlet extends HttpServlet {

    private UserService userService = UserServiceFactory.getUserService();

    public void init() {
        userService.addUser(new User(0L, "admin@ru", "admin"));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<User> allUsers = userService.getAllUsers();
        if (!allUsers.isEmpty()) {
            for (User user : allUsers) {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("notfound", "Your account is not found. Wrong password or email.");
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

}

