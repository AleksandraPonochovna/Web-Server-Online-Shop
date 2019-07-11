package controller.servlet;

import factory.UserServiceFactory;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/users/delete")
public class DeleteUserServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();
    private static final Logger logger = Logger.getLogger(DeleteUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            userService.deleteUser(Long.valueOf(id));
            logger.info("User { id = " + id + "} is deleted in db.");
        }
        response.sendRedirect("/users");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
