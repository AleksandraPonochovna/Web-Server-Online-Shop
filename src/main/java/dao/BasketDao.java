package dao;

import model.Basket;
import model.Product;
import model.User;

import java.util.Optional;
import java.util.List;

public interface BasketDao {

    void add(Basket basket);

    void addProduct(Basket basket, Product product);

    List<Product> getProducts(Basket basket);

    int getCountProducts(Basket basket);

    Optional<Basket> getBasketByUser(User user);

}
