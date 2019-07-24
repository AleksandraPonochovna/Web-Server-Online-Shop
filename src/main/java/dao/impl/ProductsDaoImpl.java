package dao.impl;

import dao.ProductsDao;
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

public class ProductsDaoImpl implements ProductsDao {

    private static final Logger logger = Logger.getLogger(ProductsDaoImpl.class);
    private static final String ADD_PRODUCT_IN_DB_SQL = "INSERT INTO products (name, description, " +
            "price) VALUES (?, ?, ?)";
    private static final String GET_ALL_PRODUCTS_FROM_DB = "SELECT * FROM products";
    private static final String DELETE_PRODUCT_FROM_DB = "DELETE FROM products WHERE id = ?";
    private static final String GET_PRODUCT_BY_ID_FROM_DB = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ID_BY_PRODUCT_FROM_DB = "SELECT id FROM products WHERE name = ?";
    private static final String EDIT_PRODUCT = "UPDATE products SET name = ?, description = ?, " +
            "price = ? WHERE id = ?";

    @Override
    public void addProduct(Product product) {
        addProduct(product.getName(), product.getDescription(), product.getPrice());
    }

    @Override
    public void addProduct(String name, String description, Float price) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_IN_DB_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setFloat(3, price);
            preparedStatement.execute();
            logger.info("Product with name " + name + " added in DB.");
        } catch (SQLException e) {
            logger.error("Product with name " + name + " can't be added in DB.");
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
                        resultSet.getFloat("price"));
                products.add(productFromDb);
            }
            return products;
        } catch (SQLException e) {
            logger.error("Products can't be taken from DB.");
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_FROM_DB);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Product with id {" + id + "} removed in db.");
        } catch (SQLException e) {
            logger.error("Product {id = " + id + "} is not found for deleting in db. ");
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
                        resultSet.getFloat("price"));
                return Optional.of(productFromDb);
            }
        } catch (SQLException e) {
            logger.error("Product {id = " + id + "} is not found.");
        }
        return Optional.empty();
    }

    @Override
    public Long getIdProduct(Product product) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_PRODUCT_FROM_DB);
            preparedStatement.setString(1, product.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            logger.error(product + " is not found.");
        }
        return null;
    }

    @Override
    public void editProduct(Product product, String newName, String newDesc, Float newPrice) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PRODUCT);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newDesc);
            preparedStatement.setFloat(3, newPrice);
            preparedStatement.setLong(4, product.getId());
            preparedStatement.execute();
            logger.info(product + " was edit in db.");
        } catch (SQLException e) {
            logger.error(product + " can't be edit in db.");
        }
    }

}
