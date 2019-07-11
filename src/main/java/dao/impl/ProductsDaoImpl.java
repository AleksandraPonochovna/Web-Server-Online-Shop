package dao.impl;

import dao.ProductsDao;
import database.Database;
import model.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductsDaoImpl implements ProductsDao {

    private static final Logger logger = Logger.getLogger(ProductsDaoImpl.class);
    private Product product;

    @Override
    public void addProduct(Product product) {
        Database.products.add(product);
    }

    @Override
    public void addProduct(Long id, String name, String description, Double price) {
        product = new Product(id, name, description, price);
        Database.products.add(product);
        logger.info("User " + product + "added in DB");
    }

    @Override
    public List<Product> getAllProducts() {
        return Database.products;
    }

    @Override
    public void deleteProduct(Long id) {
        Database.products.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .ifPresent(x -> Database.products.remove(x));
        logger.info("User with email " + getById(id).getName()  + "removed in db");
    }

    @Override
    public Product getById(Long id) {
        for (Product product : Database.products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

}
