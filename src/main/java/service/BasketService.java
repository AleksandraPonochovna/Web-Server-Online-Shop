package service;

import model.Basket;
import model.Product;
import model.User;

public interface BasketService {

    void createBasket(User user);

    void addProductInBasket(Long id, Product product);

    Basket get(Long id);

}
