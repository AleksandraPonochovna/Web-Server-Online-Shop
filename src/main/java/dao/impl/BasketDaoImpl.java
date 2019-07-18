package dao.impl;

import dao.BasketDao;
import database.Database;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;

import java.util.Optional;

public class BasketDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketDaoImpl.class);

    @Override
    public void createBasket(User user) {
        Basket basket = new Basket(user);
        Database.baskets.add(basket);
        logger.info(user + "creates a new basket");
    }

    @Override
    public void addProductInBasket(Long id, Product product) {
        Optional<Basket> optBasket = Optional.ofNullable(get(id));
        if (optBasket.isPresent()) {
            Basket basket = optBasket.get();
            basket.getBasket().add(product);
            logger.info(product + " added in " + basket);
        } else {
            logger.info(product + " can't be added in basket.");
        }
    }

    @Override
    public Basket get(Long id) {
        Optional<Basket> optBasket = Database.baskets.stream()
                .filter(x -> x.getUser().getId().equals(id))
                .findFirst();
        if (optBasket.isPresent()) {
            Basket basket = optBasket.get();
            return basket;
        } else {
            logger.info(optBasket + " is null.");
            return null;
        }
    }

}
