package dao;

import model.Basket;
import model.Product;
import model.User;

import java.util.Optional;
import java.util.Set;

public interface BasketDao {

    void add(Basket basket);

    void addProduct(Basket basket, Product product);

    Set<Product> getProducts(Basket basket);

    int getCountProducts(Basket basket);

    Optional<Basket> getBasketFor(User user);

}
