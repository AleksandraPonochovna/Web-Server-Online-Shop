package service;

import model.Order;
import model.User;

import java.util.Optional;

public interface OrderService {

    void addOrder(Order order);

    Long getBasketIdForOrder(Order order);

    Optional<Order> getCurrentOrderFor(User user);

}
