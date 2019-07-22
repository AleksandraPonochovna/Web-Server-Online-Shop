package factory;

import service.BasketService;
import service.impl.BasketServiceImpl;

public class BasketServiceFactory {

    private static BasketService instance;

    private BasketServiceFactory() {
    }

    public static BasketService getBasketService() {
        if (instance == null) {
            instance = new BasketServiceImpl();
        }
        return instance;
    }

}
