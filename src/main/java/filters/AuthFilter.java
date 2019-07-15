package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/admin*")
public class AuthFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

        if (nonNull(user) && user.getRole().equals("admin")) {
            httpRequest.getRequestDispatcher("/users.jsp").forward(httpRequest, httpResponse);
        } else if (nonNull(user) && user.getRole().equals("user")) {
            httpRequest.getRequestDispatcher("/products.jsp").forward(httpRequest, httpResponse);
        } else {
            httpRequest.setAttribute("unknown", "The password or email are wrong. Try again");
            httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

}
