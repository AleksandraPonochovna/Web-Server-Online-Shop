package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/sign", loadOnStartup = 1)
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(SignInServlet.class);

    @Override
    public void init() {
        userService.addUser(0L, "admin@ru", "admin");
        logger.info("Test user is added.");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        boolean isInput = false;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (userService.getAllUsers().isEmpty()) {
            init();
        }
        for (User user : userService.getAllUsers()) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                logger.info("User {" + email + " " + password + " sign in.");
                isInput = true;
                response.sendRedirect("/users");
            }
        }
        if (!isInput) {
            request.setAttribute("not_found", "User is not found. Try again.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

