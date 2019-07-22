package database;

import model.Basket;
import model.Order;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public static List<Product> products = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Basket> baskets = new ArrayList<>();

}
