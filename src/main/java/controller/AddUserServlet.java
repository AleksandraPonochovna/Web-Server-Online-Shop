package controller;

import factory.UserServiceFactory;
import model.User;
import service.UserService;
import util.DigestMessageGenerate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet(value = "/admin/add/user")
public class AddUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

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
            String repeatPass = request.getParameter("rpassword");
            String salt = DigestMessageGenerate.generateSalt();
            String securePass = DigestMessageGenerate.encryptSha256AndSalt(password, salt);
            String repeatSecurePass = DigestMessageGenerate.encryptSha256AndSalt(repeatPass, salt);
            String role = request.getParameter("role");
            if (nonNull(securePass) && securePass.equals(repeatSecurePass)) {
                User user = new User(email, securePass, role, salt);
                userService.addUser(user);
                response.sendRedirect("/admin/users");
            } else {
                request.setAttribute("email", email);
                request.setAttribute("passwordsError", "Your passwords are not equal.");
                request.getRequestDispatcher("/add_user.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("validFields", "It isn't rightly. Enter the correct values.");
            request.getRequestDispatcher("/add_user.jsp").forward(request, response);
        }
    }

}

