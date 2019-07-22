package dao.impl;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private static final String CREATE_BASKET_IN_DB = "INSERT INTO basket (user_id) VALUES %d";
    private static final String ADD_PRODUCT_IN_BASKET = "INSERT INTO product_basket (product_id, basket_id) " +
            "VALUES (%d, %d)";
    private static final String GET_PRODUCTS_BY_USER_FROM_DB = "SELECT products.id, name, description, price " +
            "FROM products INNER JOIN basket WHERE basket.id = 1";

    @Override
    public void createBasket(User user) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(CREATE_BASKET_IN_DB, user.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info(user + " creates a basket.");
        } catch (SQLException e) {
            logger.info(user + " can't create a basket.");
        }
    }

    @Override
    public void addProductInBasket(Long id, Product product) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(ADD_PRODUCT_IN_BASKET, product.getId(), id);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info(product + " added in basket.");
        } catch (SQLException e) {
            logger.info(product + " can't be added in basket.");
        }
    }

    @Override
    public List<Product> getProducts(User user) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnector.connect()){
            String sql = String.format(GET_PRODUCTS_BY_USER_FROM_DB, user.getId());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"));
                products.add(productFromDb);
                return products;
            }
        } catch (SQLException e) {
            logger.info("User's basket " + user + "} is not found.");
        }
        return null;
    }

    @Override
    public int getCountProducts(User user) {
        List<Product> products = getProducts(user);
        return products.size();
    }

    @Override
    public Basket getBasket(User user) {
        return null; //some implementation
    }

}
