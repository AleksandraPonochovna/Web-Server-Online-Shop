package service.impl;

import dao.OrderDao;
import factory.OrderDaoFactory;
import model.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getOrderDap();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

}
