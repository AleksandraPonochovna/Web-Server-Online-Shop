package service.impl;

import dao.BasketDao;
import factory.BasketDaoFactory;
import model.Basket;
import model.Product;
import model.User;
import service.BasketService;

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
    public Basket get(Long id) {
        return basketDao.get(id);
    }

}
