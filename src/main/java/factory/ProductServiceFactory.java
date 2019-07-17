package factory;

import service.ProductService;
import service.impl.ProductServiceImpl;

public class ProductServiceFactory {

    private static ProductService instance;

    private ProductServiceFactory() {
    }

    public static ProductService getProductService() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

}
