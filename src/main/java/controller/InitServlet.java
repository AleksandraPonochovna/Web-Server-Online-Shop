package controller;

import factory.UserServiceFactory;
import service.UserService;
import util.IdGeneratorUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private static final UserService userService = UserServiceFactory.getUserService();

    @Override
    public void init() {
        userService.addUser(IdGeneratorUtil.getUserId(), "admin@ru", "admin", "admin");
        userService.addUser(IdGeneratorUtil.getUserId(), "user@ru", "user", "user");
    }

}
