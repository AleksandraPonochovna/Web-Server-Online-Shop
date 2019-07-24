package dao.impl;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private static final String CREATE_BASKET_IN_DB = "INSERT INTO basket (user_id) VALUES ?";
    private static final String ADD_PRODUCT_IN_BASKET = "INSERT INTO product_basket (product_id, " +
            "basket_id) VALUES (?, ?)";
    private static final String GET_PRODUCTS_BY_USER_FROM_DB = "SELECT basket_id, product_id, " +
            "name, description, price FROM basket INNER JOIN product_basket INNER JOIN products " +
            "INNER JOIN users WHERE products.id = product_id AND basket_id = basket.id AND " +
            "user_id = users.id AND users.id = ?";
    private static final String GET_USER_FOR_BASKET_FROM_DB = "SELECT basket.id, users.id, " +
            "email, password, role FROM basket INNER JOIN users WHERE user_id = ? " +
            "AND users.id = user_id";

    @Override
    public void createBasket(User user) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BASKET_IN_DB);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.execute();
            logger.info(user + " creates a basket.");
        } catch (SQLException e) {
            logger.error(user + " can't create a basket.");
        }
    }

    @Override
    public void addProductInBasket(User user, Product product) {
        try (Connection connection = DBConnector.connect()) {
            Basket basket = getBasket(user);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_IN_BASKET);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setLong(2, basket.getId());
            preparedStatement.execute();
            logger.info(product + " added in basket.");
        } catch (SQLException e) {
            logger.error(product + " can't be added in basket.");
        }
    }

    @Override
    public List<Product> getProducts(User user) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnector.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_USER_FROM_DB);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"));
                products.add(productFromDb);
                return products;
            }
        } catch (SQLException e) {
            logger.error("User's basket " + user + " is not found.");
        }
        return null;
    }

    @Override
    public int getCountProducts(User user) {
        List<Product> products = getProducts(user);
        if (isNull(products)) {
            return 0;
        }
        return products.size();
    }

    @Override
    public Basket getBasket(User user) {
        List<Product> products = getProducts(user);
        try (Connection connection = DBConnector.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FOR_BASKET_FROM_DB);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long basketId = resultSet.getLong("id");
                Long userId = resultSet.getLong("users.id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User userFromDb = new User(userId, email, password, role);
                Basket basketFromDb = new Basket(basketId, userFromDb, products);
                return basketFromDb;
            }
        } catch (SQLException e) {
            logger.error("User's basket " + user + " is not found.");
        }
        return null;
    }

    @Override
    public boolean isExist(User user) {
        if (nonNull(getBasket(user))) {
            return true;
        }
        return false;
    }

}
