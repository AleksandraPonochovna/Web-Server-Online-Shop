package dao.impl.hibernate;

import dao.OrderDao;
import model.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class OrderDaoHibernateImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoHibernateImpl.class);

    @Override
    public void addOrder(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(order + "can't be added in DB", e);
        }
    }

}
