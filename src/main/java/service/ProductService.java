package service;

import model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Product getById(Long id);

}
