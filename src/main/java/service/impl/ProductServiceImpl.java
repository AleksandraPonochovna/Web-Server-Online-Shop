package service.impl;

import dao.ProductsDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static final ProductsDao productsDao = ProductDaoFactory.getProductDao();

    @Override
    public void addProduct(Product product) {
        productsDao.addProduct(product);
    }

    @Override
    public void addProduct(String name, String description, Float price) {
        productsDao.addProduct(name, description, price);
    }

    @Override
    public List<Product> getAllProducts() {
        return productsDao.getAllProducts();
    }

    @Override
    public void deleteProduct(Long id) {
        productsDao.deleteProduct(id);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productsDao.getById(id);
    }

    @Override
    public Long getIdProduct(Product product) {
        return productsDao.getIdProduct(product);
    }

    @Override
    public void editProduct(Product product, String newName, String newDesc, Float newPrice) {
        productsDao.editProduct(product, newName, newDesc, newPrice);
    }

}
