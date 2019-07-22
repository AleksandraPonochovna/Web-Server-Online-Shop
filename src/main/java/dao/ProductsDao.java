package dao;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsDao {

    void addProduct(Product product);

    void addProduct(String name, String description, Float price);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Optional<Product> getById(Long id);

    Long getIdProduct(Product product);

    void editProduct(Product product, String newName, String newDesc, Float newPrice);

}
