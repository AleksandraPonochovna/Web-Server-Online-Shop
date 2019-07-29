package service.impl;

import dao.OrderDao;
import factory.hibernate.OrderDaoHibernateFactory;
import model.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderDaoHibernateFactory.getOrderDao();

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

}
