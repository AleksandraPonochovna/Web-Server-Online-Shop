package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import util.IdGeneratorUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/add/user")
public class AddUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(AddUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add_user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repeatPassword = request.getParameter("rpassword");
            String role = request.getParameter("role");
            if (password.equals(repeatPassword)) {
                User user = new User(IdGeneratorUtil.getUserId(), email, password, role);
                userService.addUser(user);
                logger.info("User {" + email + " " + password + "} is added in db.");
                response.sendRedirect("/admin/users");
            } else {
                request.setAttribute("email", email);
                request.setAttribute("passwordsError", "Your passwords are not equal.");
                logger.info("Passwords {" + password + " " + repeatPassword + "} are not equals.");
                request.getRequestDispatcher("/add_user.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("validFields", "It isn't rightly. Enter the correct values.");
            request.getRequestDispatcher("/add_user.jsp").forward(request, response);
        }
    }

}

