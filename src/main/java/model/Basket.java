package model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private User user;
    private List<Product> basket = new ArrayList<>();

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
        return basket;
    }

    public void setBasket(List<Product> basket) {
        this.basket = basket;
    }

    public int getBasketSize() {
        return basket.size();
    }

    public void refreshBasket(){
        basket = new ArrayList<>();
    }
}
