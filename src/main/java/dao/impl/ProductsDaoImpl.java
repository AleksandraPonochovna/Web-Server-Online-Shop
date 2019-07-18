package dao.impl;

import dao.ProductsDao;
import database.Database;
import model.Product;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductsDaoImpl implements ProductsDao {

    private static final Logger logger = Logger.getLogger(ProductsDaoImpl.class);

    @Override
    public void addProduct(Product product) {
        Database.products.add(product);
    }

    @Override
    public void addProduct(Long id, String name, String description, Double price) {
        Product product = new Product(id, name, description, price);
        Database.products.add(product);
        logger.info("Product " + product + "added in DB");
    }

    @Override
    public List<Product> getAllProducts() {
        return Database.products;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optProduct = getById(id);
        if (optProduct.isPresent()) {
            Product productForDelete = optProduct.get();
            Database.products.remove(productForDelete);
            logger.info("Product with name " + productForDelete.getName() + " removed in db");
        } else {
            logger.info("Product for deleting {id = " + id + "} not found");
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Database.products.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst();
    }

}
