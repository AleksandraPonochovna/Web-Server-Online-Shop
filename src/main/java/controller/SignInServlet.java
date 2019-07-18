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

@WebServlet(value = "/sign")
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> optUser = userService.getByEmail(email);

        if (optUser.isPresent()) {
            user = optUser.get();
        }
        if (user != null && user.getPassword().equals(password))  {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("roleCurrentUser", user.getRole());
            if (user.getRole().equals("admin")) {
                response.sendRedirect("/admin/users");
            } else {
                response.sendRedirect("/products");
            }
        } else {
            request.setAttribute("unknown", "The user is not found. Try again");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}

