package dao.impl;

import dao.OrderDao;
import model.Order;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final String ADD_ORDER_IN_DB = "INSERT INTO `order` (first_name, last_name, " +
            "number_phone, street_name, house_number, basket_id) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_IN_DB);
            preparedStatement.setString(1, order.getFirstName());
            preparedStatement.setString(2, order.getLastName());
            preparedStatement.setString(3, order.getNumberOfPhone());
            preparedStatement.setString(4, order.getStreetName());
            preparedStatement.setString(5, order.getHouseNumber());
            preparedStatement.setLong(6, order.getBasket().getId());
            preparedStatement.execute();
            logger.info(order + " added in DB.");
        } catch (SQLException e) {
            logger.error(order + " can't be added in DB.");
        }
    }

}
