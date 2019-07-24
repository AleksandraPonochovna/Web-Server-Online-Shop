package model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private Long id;
    private User user;
    private List<Product> products;

    public Basket(Long id, User user, List<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public Basket(Long id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public Basket(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getBasket() {
        return products;
    }

    public void setBasket(List<Product> products) {
        this.products = products;
    }

    public int getBasketSize() {
        return products.size();
    }

}
