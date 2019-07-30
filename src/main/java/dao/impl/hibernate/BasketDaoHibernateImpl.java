package dao.impl.hibernate;

import dao.BasketDao;
import model.Basket;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BasketDaoHibernateImpl implements BasketDao {

    private static final String GET_BASKET_BY_USER = "FROM Basket WHERE user = :user " +
            "ORDER BY id DESC";
    private static final Logger logger = Logger.getLogger(BasketDaoHibernateImpl.class);

    @Override
    public void add(Basket basket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(basket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(basket + "can't be update in DB", e);
        }
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            basket.getProducts().add(product);
            session.update(basket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(product + "can't be added in " + basket, e);
        }
    }

    @Override
    public List<Product> getProducts(Basket basket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            transaction.commit();
            return basket.getProducts();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(basket + "can't be taken from DB", e);
        }
        return Collections.emptyList();
    }

    @Override
    public int getCountProducts(Basket basket) {
        if (basket.getProducts() == null) {
            return 0;
        }
        return basket.getProducts().size();
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(GET_BASKET_BY_USER);
            query.setParameter("user", user);
            query.setMaxResults(1);
            Optional<Basket> optBasket = query.uniqueResultOptional();
            transaction.commit();
            return optBasket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Basket for " + user + "can't be taken from DB", e);
        }
        return Optional.empty();
    }

}
