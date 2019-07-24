package controller;

import factory.UserServiceFactory;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import util.DigestMessageGenerate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/admin/users/edit")
public class EditUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(EditUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Optional<User> optUser = null;
        if (!request.getParameter("id").isEmpty()) {
            Long id = Long.valueOf(request.getParameter("id"));
            optUser = userService.getById(id);
            request.setAttribute("id", id);
        } else if (!request.getParameter("email").isEmpty()) {
            String email = request.getParameter("email");
            optUser = userService.getByEmail(email);
            request.setAttribute("email", email);
        }
        if (optUser.isPresent()) {
            User user = optUser.get();
            request.setAttribute("oldEmail", user.getEmail());
            request.setAttribute("oldPassword", user.getPassword());
            request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("id") != null) {
                Long id = Long.valueOf(request.getParameter("id"));
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String hashPassword = DigestMessageGenerate.sha256ToHex(password);
                Optional<User> optUser = userService.getById(id);
                if (optUser.isPresent()) {
                    User user = optUser.get();
                    userService.editUser(user, email, hashPassword);
                    response.sendRedirect("/admin/users");
                }
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("validValues", "Something is wrong. Try again. ");
            request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
        }
    }
}

