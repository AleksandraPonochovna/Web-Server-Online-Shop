package factory.hibernate;

import dao.ProductDao;
import dao.impl.hibernate.ProductDaoHibernateImpl;

public class ProductDaoHibernateFactory {

    private static ProductDao instance;

    private ProductDaoHibernateFactory() {
    }

    public static ProductDao getProductDao() {
        if (instance == null) {
            instance = new ProductDaoHibernateImpl();
        }
        return instance;
    }
}
