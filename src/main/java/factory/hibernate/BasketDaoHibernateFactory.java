package factory.hibernate;

import dao.BasketDao;
import dao.impl.hibernate.BasketDaoHibernateImpl;

public class BasketDaoHibernateFactory {

    private static BasketDao instance;

    private BasketDaoHibernateFactory() {
    }

    public static BasketDao getBasketDao() {
        if (instance == null) {
            instance = new BasketDaoHibernateImpl();
        }
        return instance;
    }

}
