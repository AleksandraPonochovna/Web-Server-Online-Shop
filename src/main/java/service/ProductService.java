package service;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(Product product);

    void addProduct(Long id, String name, String description, Double price);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Optional<Product> getById(Long id);

}
