package dao;

import model.Basket;
import model.Product;
import model.User;

import java.util.List;

public interface BasketDao {

    void createBasket(User user);

    void addProductInBasket(Long id, Product product);

    List<Product> getProducts(User user);

    int getCountProducts(User user);

    Basket getBasket(User user);

}
