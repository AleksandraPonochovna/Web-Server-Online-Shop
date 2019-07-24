package service.impl;

import dao.OrderDao;
import factory.OrderDaoFactory;
import model.Order;
import model.User;
import service.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoFactory.getOrderDap();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public Optional<Order> getCurrentOrderFor(User user) {
        return orderDao.getCurrentOrderFor(user);
    }

}
