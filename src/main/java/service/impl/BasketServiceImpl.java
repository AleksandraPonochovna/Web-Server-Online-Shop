package service.impl;

import dao.BasketDao;
import factory.hibernate.BasketDaoHibernateFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;

import java.util.Optional;
import java.util.Set;

public class BasketServiceImpl implements BasketService {

    private static final BasketDao basketDao = BasketDaoHibernateFactory.getBasketDao();

    @Override
    public void add(Basket basket) {
        basketDao.add(basket);
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        basketDao.addProduct(basket, product);
    }

    @Override
    public Set<Product> getProducts(Basket basket) {
        return basketDao.getProducts(basket);
    }

    @Override
    public int getCountProducts(Basket basket) {
        return basketDao.getCountProducts(basket);
    }

    @Override
    public Optional<Basket> getBasketBy(User user) {
        return basketDao.getBasketBy(user);
    }

}
