package service;

import model.Basket;
import model.Product;
import model.User;

import java.util.List;

public interface BasketService {

    void createBasket(User user);

    void addProductInBasket(User user, Product product);

    List<Product> getProducts(User user);

    int getCountProducts(User user);

    Basket getBasket(User user);

    boolean isExist(User user);

}
