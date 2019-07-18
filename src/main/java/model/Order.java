package model;

public class Order {

    private Long id;
    private String firstName;
    private String lastName;
    private String numberOfPhone;
    private String streetName;
    private String houseNumber;
    private String enteredCode;
    private Basket basket;
    private User user;

    public Order(Long id, String firstName, String lastName, String numberOfPhone, String streetName,
                 String houseNumber, String enteredCode, Basket basket, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPhone = numberOfPhone;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.enteredCode = enteredCode;
        this.basket = basket;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumberOfPhone() {
        return numberOfPhone;
    }

    public void setNumberOfPhone(String numberOfPhone) {
        this.numberOfPhone = numberOfPhone;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getEnteredCode() {
        return enteredCode;
    }

    public void setEnteredCode(String enteredCode) {
        this.enteredCode = enteredCode;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
