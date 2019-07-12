package filters;

import factory.UserServiceFactory;
import service.UserService;
import util.IdGeneratorUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = {"/sign", "/admin*"})
public class AuthFilter implements Filter {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    public void init(FilterConfig filterConfig) {
        userService.addUser(IdGeneratorUtil.getUserId(), "admin@ru", "admin", "admin");
        userService.addUser(IdGeneratorUtil.getUserId(), "user@ru", "user", "user");
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

        if (nonNull(session) && userService.userIsExist(email, password)) {
            final String role = userService.getRoleByEmailPassword(email, password);
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, resp, role);
        } else {
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

    private void moveToMenu(HttpServletRequest req,
                            HttpServletResponse resp, String role) throws ServletException, IOException {
        if (role.equals("admin")) {
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } else if (role.equals("user")) {
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

}
