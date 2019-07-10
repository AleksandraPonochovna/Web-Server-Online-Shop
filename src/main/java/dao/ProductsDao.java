package dao;

import model.Product;

import java.util.List;

public interface ProductsDao {

    void addProduct(Product t);

    List<Product> getAllProducts();

}
