package dao.impl;

import dao.OrderDao;
import model.Order;
import org.apache.log4j.Logger;
import util.DBConnector;
import util.IdGeneratorUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final String ADD_ORDER_IN_DB = "INSERT INTO order (first_name, last_name, number_phone," +
            "house_number, entered_code, basket_id) VALUES ('%s', '%s', '%s', '%s', '%s', %d)";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(ADD_ORDER_IN_DB, order.getFirstName(), order.getLastName(),
                    order.getNumberOfPhone(), order.getHouseNumber(), order.getEnteredCode(), IdGeneratorUtil.getBasketId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Order " + order + " added in DB.");
        } catch (SQLException e) {
            logger.info("Order " + order + " can't be added in DB.");
        }
    }

}
