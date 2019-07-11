package controller;

import factory.UserServiceFactory;
import org.apache.log4j.Logger;
import service.UserService;
import util.IdGeneratorUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/add/user")
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
            if (password.equals(repeatPassword)) {
                userService.addUser(IdGeneratorUtil.getUserId(), email, password);
                logger.info("User {" + email + " " + password + "} is added in db.");
                response.sendRedirect("/users");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                request.setAttribute("email", email);
                request.setAttribute("error", "Your passwors are not equal.");
                logger.info("Passwords {" + password + " " + repeatPassword + "} are not equals.");
                request.getRequestDispatcher("/add_user.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("valid", "It isn't rightly. Enter the correct values.");
            request.getRequestDispatcher("/add_user.jsp").forward(request, response);
        }
    }

}
