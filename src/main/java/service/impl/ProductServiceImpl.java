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
    public List<Product> getAllProducts() {
        return productsDao.getAllProducts();
    }

    @Override
    public void deleteProduct(Long id) {
        for (Product product : productsDao.getAllProducts()) {
            if (product.getId().equals(id)) {
                productsDao.getAllProducts().remove(product);
            }
        }
    }

    @Override
    public Product getById(Long id) {
        for (Product product : productsDao.getAllProducts()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

}
