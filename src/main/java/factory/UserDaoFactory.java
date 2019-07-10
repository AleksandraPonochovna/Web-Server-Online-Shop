package factory;

import dao.UsersDao;
import dao.impl.UsersDaoImpl;

public class UserDaoFactory {

    private static UsersDao instance;

    private UserDaoFactory() {
    }

    public static UsersDao getUserDao() {
        if (instance == null) {
            instance = new UsersDaoImpl();
        }
        return instance;
    }

}
