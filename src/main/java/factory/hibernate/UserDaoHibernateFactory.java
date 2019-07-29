package factory.hibernate;

import dao.UserDao;
import dao.impl.hibernate.UserDaoHibernateImpl;

public class UserDaoHibernateFactory {

    private static UserDao instance;

    private UserDaoHibernateFactory() {
    }

    public static UserDao getUserDao() {
        if (instance == null) {
            instance = new UserDaoHibernateImpl();
        }
        return instance;
    }

}
