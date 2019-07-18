package factory;

import dao.BasketDao;
import dao.impl.BasketDaoImpl;

public class BasketDaoFactory {

    private static BasketDao instance;

    private BasketDaoFactory() {
    }

    public static BasketDao getBasketDao() {
        if (instance == null) {
            instance = new BasketDaoImpl();
        }
        return instance;
    }

}
