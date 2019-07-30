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
import java.util.Optional;
import java.util.List;

import static java.util.Objects.isNull;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);
    private static final String CREATE_BASKET_IN_DB = "INSERT INTO basket (user_id) VALUES ?";
    private static final String ADD_PRODUCT_IN_BASKET = "INSERT INTO product_basket (product_id, " +
            "basket_id) VALUES (?, ?)";
    private static final String GET_PRODUCTS_FROM_DB = "SELECT basket_id, product_id, " +
            "name, description, price FROM basket INNER JOIN product_basket INNER JOIN product " +
            "INNER JOIN user WHERE product.id = product_id AND basket_id = basket.id AND " +
            "user_id = user.id AND user.id = ?";

    @Override
    public void add(Basket basket) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BASKET_IN_DB);
            preparedStatement.setLong(1, 1);
            preparedStatement.execute();
            logger.info(basket + " was added in DB.");
        } catch (SQLException e) {
            logger.error(basket + " can't be added in DB.");
        }
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        try (Connection connection = DBConnector.connect()) {
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
    public List<Product> getProducts(Basket basket) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnector.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_FROM_DB);
            preparedStatement.setLong(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                products.add(productFromDb);
                return products;
            }
        } catch (SQLException e) {
            logger.error("Basket " + basket + " is not found.");
        }
        return null;
    }

    @Override
    public int getCountProducts(Basket basket) {
        List<Product> products = getProducts(basket);
        if (isNull(products)) {
            return 0;
        }
        return products.size();
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        return Optional.empty();
    }

}
