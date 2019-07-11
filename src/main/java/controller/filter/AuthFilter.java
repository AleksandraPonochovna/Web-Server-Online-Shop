package controller.filter;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(value = "/sign")
public class AuthFilter implements Filter {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    public void init(FilterConfig filterConfig) {
        userService.addUser(0L, "admin@ru", "admin", User.ROLE.ADMIN);
        userService.addUser(0L, "user@ru", "user", User.ROLE.USER);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final HttpSession session = req.getSession();
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        if (nonNull(session) && nonNull(req.getAttribute("email")) && nonNull(req.getAttribute("password"))) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            moveToMenu(req, resp, role);
        } else if (nonNull(session) && userService.userIsExist(email, password)) {
            final User.ROLE role = userService.getRoleByEmailPassword(email, password);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("role", role);
            moveToMenu(req, resp, role);
        } else {
            moveToMenu(req, resp, User.ROLE.UNKNOWN);
        }

    }

    private void moveToMenu(HttpServletRequest req,
                            HttpServletResponse resp, User.ROLE role) throws ServletException, IOException {
        if (role.equals(User.ROLE.ADMIN)) {
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else if (role.equals(User.ROLE.USER)) {
            req.getRequestDispatcher("/user_account.jsp").forward(req, resp);
        } else {
            req.setAttribute("unknown", "Email or password are wrong. Repeat.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

}
