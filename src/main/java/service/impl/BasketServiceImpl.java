package service.impl;

import dao.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;

import java.util.List;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoFactory.getBasketDao();

    @Override
    public void createBasket(User user) {
        basketDao.createBasket(user);
    }

    @Override
    public void addProductInBasket(Long id, Product product) {
        basketDao.addProductInBasket(id, product);
    }

    @Override
    public List<Product> getProducts(User user) {
        return basketDao.getProducts(user);
    }

    @Override
    public int getCountProducts(User user) {
        return basketDao.getCountProducts(user);
    }

    @Override
    public Basket getBasket(User user) {
        return basketDao.getBasket(user);
    }

}
