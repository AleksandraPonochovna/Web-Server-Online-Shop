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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/sign", loadOnStartup = 1)
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    public void init() throws ServletException {
        User admin = new User("admin@ru",
                "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "admin");
        User user = new User("user@ru",
                "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb",
                "user");
        userService.addUser(user);
        userService.addUser(admin);
    }

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
        String hashPassword = DigestMessageGenerate.encryptSha256(password);
        Optional<User> optUser = userService.getByEmail(email);
        if (optUser.isPresent()) {
            user = optUser.get();
        }
        if (user != null && user.getPassword().equals(hashPassword)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("roleCurrentUser", user.getRole());
            if (user.getRole().equals("admin")) {
                response.sendRedirect("/admin/users");
            } else {
                response.sendRedirect("/products");
            }
        } else {
            request.setAttribute("unknown", "The user is not found. Try again. ");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}

