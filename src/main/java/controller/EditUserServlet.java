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
import java.util.Optional;

@WebServlet(value = "/admin/users/edit")
public class EditUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Optional<User> optUser = null;
        if (!request.getParameter("id").isEmpty()) {
            Long id = Long.valueOf(request.getParameter("id"));
            optUser = userService.getById(id);
        }
        if (!request.getParameter("email").isEmpty()) {
            String email = request.getParameter("email");
            optUser = userService.getByEmail(email);
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
        final HttpSession session = request.getSession();
        final String role = (String) session.getAttribute("role");
        if (role.equals("admin")) {
            try {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Optional<User> optUser = userService.getByEmail("email");
                if (optUser.isPresent()) {
                    User user = optUser.get();
                    user.setEmail(email);
                    user.setPassword(password);
                    request.getRequestDispatcher("/users.jsp").forward(request, response);
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("validValues", "Something is wrong. Try again.");
                request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/");
        }
    }

}
