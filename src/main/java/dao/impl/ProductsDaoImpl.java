package dao.impl;

import dao.ProductsDao;
import database.Database;
import model.Product;

import java.util.List;

public class ProductsDaoImpl implements ProductsDao {

    @Override
    public void addProduct(Product product) {
        Database.products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return Database.products;
    }

}
