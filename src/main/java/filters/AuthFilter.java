package filters;

import factory.UserServiceFactory;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = {"/sign", "/admin*"})
public class AuthFilter implements Filter {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    public void init(FilterConfig filterConfig) {
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
            Optional<String> optRole = userService.getRoleByEmailPassword(email, password);
            if (optRole.isPresent()) {
                String role = optRole.get();
                if (role.equals("admin")) {
                    req.getRequestDispatcher("/users.jsp").forward(req, resp);
                } else if (role.equals("user")) {
                    req.getRequestDispatcher("/products.jsp").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("unknown", "The password or email are wrong. Try again");
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

}
