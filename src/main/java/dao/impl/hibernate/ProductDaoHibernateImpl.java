package dao.impl.hibernate;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductDaoHibernateImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoHibernateImpl.class);
    private static final String GET_ALL_PRODUCTS = "FROM Product";

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(product + "can't be added in DB", e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Product> products = session.createQuery(GET_ALL_PRODUCTS, Product.class).list();
            transaction.commit();
            return products;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("All the products can't be taken from DB", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(product + "can't be deleted in DB", e);
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            return Optional.of(product);
        } catch (Exception e) {
            logger.error("Product id = " + id + " can't be taken from DB", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(product + "can't be update in DB", e);
        }
    }

}
