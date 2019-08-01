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

import static java.util.Objects.nonNull;

@WebServlet(value = "/sign", loadOnStartup = 1)
public class SignInServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    public void init() {
        String saltAdmin = DigestMessageGenerate.generateSalt();
        String saltUser = DigestMessageGenerate.generateSalt();
        User admin = new User("admin@ru",
                DigestMessageGenerate.encryptSha256AndSalt("admin", saltAdmin),
                "admin",
                saltAdmin);
        User user = new User("user@ru",
                DigestMessageGenerate.encryptSha256AndSalt("user", saltUser),
                "user",
                saltUser);
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
        String salt = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> optUser = userService.getByEmail(email);
        if (optUser.isPresent()) {
            user = optUser.get();
            salt = user.getSalt();
        }
        String securePass = DigestMessageGenerate.encryptSha256AndSalt(password, salt);
        if (nonNull(user) && user.getPassword().equals(securePass)) {
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

