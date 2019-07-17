package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {

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
            filterChain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("/");
        }

    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

}
