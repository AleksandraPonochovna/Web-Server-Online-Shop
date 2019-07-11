package service;

import model.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void addProduct(Long id, String name, String description, Double price);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Product getById(Long id);

}
