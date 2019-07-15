package controller;

import factory.UserServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/admin/users/delete")
public class DeleteUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        final HttpSession session = request.getSession();
        final String role = (String) session.getAttribute("role");
        if (role.equals("admin")) {
            if (id != null) {
                userService.deleteUser(Long.valueOf(id));
            }
            response.sendRedirect("/admin/users");
        } else {
            response.sendRedirect("/");
        }
    }

}
