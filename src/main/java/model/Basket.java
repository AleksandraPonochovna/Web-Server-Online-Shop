package model;

public class Basket {

    private Long id;
    private User user;

    public Basket(Long id, User user) {
        this.id = id;
        this.user = user;
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

}
