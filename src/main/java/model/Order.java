package model;

public class Order {

    private Long id;
    private String firstName;
    private String lastName;
    private String numberOfPhone;
    private String streetName;
    private String houseNumber;
    private Basket basket;
    private User user;
    private Code code;

    public Order(Long id, String firstName, String lastName, String numberOfPhone, String streetName,
                 String houseNumber, User user, Code code) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPhone = numberOfPhone;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.user = user;
        this.code = code;
    }

    public Order(String firstName, String lastName, String numberOfPhone, String streetName,
                 String houseNumber, Basket basket, User user, Code code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPhone = numberOfPhone;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.basket = basket;
        this.user = user;
        this.code = code;
    }

    public Order(Long id, String firstName, String lastName, String numberOfPhone, String streetName,
                 String houseNumber, Basket basket, User user, Code code) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPhone = numberOfPhone;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.basket = basket;
        this.user = user;
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
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
