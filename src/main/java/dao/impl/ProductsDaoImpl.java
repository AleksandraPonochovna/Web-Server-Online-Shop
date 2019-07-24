package dao.impl;

import dao.ProductsDao;
import model.Product;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ProductsDaoImpl implements ProductsDao {

    private static final Logger logger = Logger.getLogger(ProductsDaoImpl.class);
    private static final String ADD_PRODUCT_IN_DB_SQL = "INSERT INTO products (name, description, " +
            "price) VALUES ('%s', '%s', %01.2f)";
    private static final String GET_ALL_PRODUCTS_FROM_DB = "SELECT * FROM products";
    private static final String DELETE_PRODUCT_FROM_DB = "DELETE FROM products WHERE id = %d";
    private static final String GET_PRODUCT_BY_ID_FROM_DB = "SELECT * FROM products WHERE id = %d";
    private static final String GET_ID_BY_PRODUCT_FROM_DB = "SELECT id FROM products " +
            "WHERE name = '%s'";
    private static final String EDIT_PRODUCT = "UPDATE products SET name = '%s'," +
            " description = '%s', price = %01.2f WHERE id = %d";

    @Override
    public void addProduct(Product product) {
        addProduct(product.getName(), product.getDescription(), product.getPrice());
    }

    @Override
    public void addProduct(String name, String description, Float price) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(Locale.US, ADD_PRODUCT_IN_DB_SQL, name, description, price);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Product with name " + name + " added in DB.");
        } catch (SQLException e) {
            logger.info("Product with name " + name + " can't be added in DB.");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Connection connection = DBConnector.connect()) {
            List<Product> products = new ArrayList<>();
            String sql = String.format(Locale.US, GET_ALL_PRODUCTS_FROM_DB);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
            logger.info("Products can't be taken from DB.");
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        try (Connection connection = DBConnector.connect()){
            String sql = String.format(Locale.US, DELETE_PRODUCT_FROM_DB, id);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Product with id " + id + " removed in db.");
        } catch (SQLException e) {
            logger.info("Product for deleting {id = " + id + "} not found.");
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Connection connection = DBConnector.connect()){
            String sql = String.format(Locale.US, GET_PRODUCT_BY_ID_FROM_DB, id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Product productFromDb = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"));
                return Optional.of(productFromDb);
            }
        } catch (SQLException e) {
            logger.info("Product {id = " + id + "} is not found.");
        }
        return Optional.empty();
    }

    @Override
    public Long getIdProduct(Product product) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(Locale.US, GET_ID_BY_PRODUCT_FROM_DB, product.getName());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            logger.info(product + " is not found.");
        }
        return null;
    }

    @Override
    public void editProduct(Product product, String newName, String newDesc, Float newPrice) {
        try(Connection connection = DBConnector.connect()) {
            String sql = String.format(Locale.US, EDIT_PRODUCT, newName, newDesc, newPrice, product.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info(product + "was edit in db.");
        } catch (SQLException e) {
            logger.info(product + " can't be edit in db.");
        }
    }

}
