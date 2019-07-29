package service.impl;

import dao.ProductDao;
import factory.ProductDaoFactory;
import factory.hibernate.ProductDaoHibernateFactory;
import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = ProductDaoHibernateFactory.getProductDao();

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

}
