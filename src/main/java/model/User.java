package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String email;
    private String password;
    private String role;
    private List<Product> basket = new ArrayList<>();

    public List<Product> getBasket() {
        return basket;
    }

    public int getBasketSize() {
        return basket.size();
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum ROLE {
        ADMIN, USER, UNKNOWN
    }

}
