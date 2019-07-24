package dao;

import model.Order;
import model.User;

import java.util.Optional;

public interface OrderDao {

    void addOrder(Order order);

    Optional<Order> getCurrentOrderFor(User user);

}
