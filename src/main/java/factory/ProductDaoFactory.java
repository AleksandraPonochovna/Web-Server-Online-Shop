package factory;

import dao.ProductsDao;
import dao.impl.ProductsDaoImpl;

public class ProductDaoFactory {

    private static ProductsDao instance;

    private ProductDaoFactory() {
    }

    public static ProductsDao getProductDao() {
        if (instance == null) {
            instance = new ProductsDaoImpl();
        }
        return instance;
    }

}
