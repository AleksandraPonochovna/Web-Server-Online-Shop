package dao.impl.hibernate;

import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Optional;

public class CodeDaoHibernateImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeDaoHibernateImpl.class);
    private static final String GET_LAST_CODE_FOR_USER = "FROM Code WHERE user = :user " +
            "ORDER BY id DESC";

    @Override
    public void add(Code code) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(code);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(code + "can't be added in DB", e);
        }
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Code> query = session.createQuery(GET_LAST_CODE_FOR_USER);
            query.setParameter("user", user);
            query.setMaxResults(1);
            Code code = query.uniqueResult();
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Last code for " + user + " isn't found in DB", e);
        }
        return Optional.empty();
    }
}
