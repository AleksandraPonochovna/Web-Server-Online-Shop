package factory.hibernate;

import dao.OrderDao;
import dao.impl.hibernate.OrderDaoHibernateImpl;

public class OrderDaoHibernateFactory {

    private static OrderDao instance;

    private OrderDaoHibernateFactory() {
    }

    public static OrderDao getOrderDao() {
        if (instance == null) {
            instance = new OrderDaoHibernateImpl();
        }
        return instance;
    }
}
