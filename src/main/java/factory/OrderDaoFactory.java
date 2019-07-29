package factory;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;

public class OrderDaoFactory {

    private static OrderDao instance;

    private OrderDaoFactory() {
    }

    public static OrderDao getOrderDao() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

}
