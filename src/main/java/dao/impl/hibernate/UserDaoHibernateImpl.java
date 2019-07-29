package dao.impl.hibernate;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);
    private static final String GET_ALL_USERS = "FROM User";

    @Override
    public void addUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(user + "can't be added in DB", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery(GET_ALL_USERS, User.class).list();
            transaction.commit();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("All the users can't be taken from DB", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(user + "can't be deleted in DB", e);
        }
    }

    @Override
    public Optional<User> getById(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("User id = " + userId + " can't be taken from DB", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("User email = " + email + " can't be taken from DB", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(user + "can't be update in DB", e);
        }
    }

}
