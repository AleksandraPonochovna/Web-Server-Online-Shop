package service.impl;

import dao.ProductsDao;
import factory.ProductDaoFactory;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductsDao productsDao = ProductDaoFactory.getProductDao();

    @Override
    public void addProduct(Product product) {
        productsDao.addProduct(product);
    }

    @Override
    public void addProduct(Long id, String name, String description, Double price) {
        productsDao.addProduct(id, name, description, price);
    }

    @Override
    public List<Product> getAllProducts() {
        return productsDao.getAllProducts();
    }

    public void deleteProduct(Long id) {
        productsDao.deleteProduct(id);
    }

    @Override
    public Product getById(Long id) {
        return productsDao.getById(id);
    }

}
