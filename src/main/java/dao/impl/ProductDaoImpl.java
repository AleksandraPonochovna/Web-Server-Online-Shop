package dao.impl;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
    private static final String ADD_PRODUCT_IN_DB_SQL = "INSERT INTO product (name, description, " +
            "price) VALUES (?, ?, ?)";
    private static final String GET_ALL_PRODUCTS_FROM_DB = "SELECT * FROM product";
    private static final String DELETE_PRODUCT_FROM_DB = "DELETE FROM product WHERE id = ?";
    private static final String GET_PRODUCT_BY_ID_FROM_DB = "SELECT * FROM product WHERE id = ?";
    private static final String GET_ID_BY_PRODUCT_FROM_DB = "SELECT id FROM product WHERE name = ?";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, description = ?, " +
            "price = ? WHERE id = ?";

    @Override
    public void addProduct(Product product) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_IN_DB_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.execute();
            logger.info(product + " added in DB.");
        } catch (SQLException e) {
            logger.error(product + " can't be added in DB.");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Connection connection = DBConnector.connect()) {
            List<Product> products = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS_FROM_DB);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                products.add(productFromDb);
            }
            return products;
        } catch (SQLException e) {
            logger.error("Products can't be taken from DB.");
        }
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_FROM_DB);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.execute();
            logger.info(product + " removed in db.");
        } catch (SQLException e) {
            logger.error(product + " is not found for deleting in db. ");
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID_FROM_DB);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                return Optional.of(productFromDb);
            }
        } catch (SQLException e) {
            logger.error("Product {id = " + id + "} is not found.");
        }
        return Optional.empty();
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());
            preparedStatement.execute();
            logger.info(product + " was updated in db.");
        } catch (SQLException e) {
            logger.error(product + " can't be updated in db.");
        }
    }

}
