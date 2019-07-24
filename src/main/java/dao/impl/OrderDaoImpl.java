package dao.impl;

import dao.OrderDao;
import model.Code;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final String ADD_ORDER_IN_DB = "INSERT INTO `order` (first_name, last_name, " +
            "number_phone, street_name, house_number, basket_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ORDER_BY_USER = "SELECT order.id, first_name, last_name, " +
            "number_phone, street_name, house_number, code FROM `order` INNER JOIN basket " +
            "INNER JOIN code WHERE basket_id = basket.id AND user_id = ? AND user_id = code.id_" +
            "user LIMIT 1";

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

    @Override
    public Optional<Order> getCurrentOrderFor(User user) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Code code = new Code(
                        resultSet.getLong("id"),
                        resultSet.getString("code"),
                        user.getId());
                Order order = new Order(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("number_phone"),
                        resultSet.getString("street_name"),
                        resultSet.getString("house_number"),
                        user,
                        code);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.info("Last order for " + user + " can't be taken from DB.");
        }
        return Optional.empty();
    }
}
