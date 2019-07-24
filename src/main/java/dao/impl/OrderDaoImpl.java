package dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderDao;
import model.*;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.*;
import java.util.Optional;


public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static final String ADD_ORDER_IN_DB = "INSERT INTO `order` (first_name, last_name, number_phone, street_name, " +
            "house_number, basket_id) VALUES ('%s', '%s', '%s', '%s', '%s', %d)";
    private static final String GET_BASKET_ID_FROM_DB = "SELECT basket_id FROM `order` INNER JOIN basket" +
            " WHERE order.id = %d LIMIT 1";
    private static final String GET_ORDER_BY_USER = "SELECT order.id, first_name, last_name, number_phone, street_name," +
            " house_number, code FROM `order` INNER JOIN basket INNER JOIN code WHERE basket_id = basket.id " +
            "AND user_id = %d AND user_id = code.id_user LIMIT 1";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(ADD_ORDER_IN_DB, order.getFirstName(), order.getLastName(), order.getNumberOfPhone(),
                    order.getStreetName(), order.getHouseNumber(), order.getBasket().getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("Order " + order + " added in DB.");
        } catch (SQLException e) {
            logger.info("Order " + order + " can't be added in DB.");
        }
    }

    @Override
    public Long getBasketIdForOrder(Order order) {
        try (Connection connection = DBConnector.connect()) {
            String getBasketId = String.format(GET_BASKET_ID_FROM_DB, order.getId());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getBasketId);
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            logger.info("Basket ID for " + order + " can't be taken from DB.");
        }
        return null;
    }

    @Override
    public Optional<Order> getCurrentOrderFor(User user) {
        try (Connection connection = DBConnector.connect()) {
             String sql = String.format(GET_ORDER_BY_USER, user.getId());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
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
