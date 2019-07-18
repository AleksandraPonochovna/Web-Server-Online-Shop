package dao.impl;

import dao.OrderDao;
import database.Database;
import model.Order;
import org.apache.log4j.Logger;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public void addOrder(Order order) {
        Database.orders.add(order);
        logger.info(order + "added in DB");
    }

}
