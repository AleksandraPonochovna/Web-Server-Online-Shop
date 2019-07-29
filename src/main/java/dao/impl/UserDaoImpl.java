package dao.impl;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final String ADD_USER_IN_DB = "INSERT INTO user (email, password, role) " +
            "VALUES (?, ?, ?)";
    private static final String GET_ALL_USERS_FROM_DB = "SELECT * FROM user";
    private static final String DELETE_USER_FROM_DB = "DELETE FROM user WHERE id = ?";
    private static final String GET_USER_BY_ID_FROM_DB = "SELECT * FROM user WHERE id = ?";
    private static final String GET_USER_BY_EMAIL_FROM_DB = "SELECT * FROM user WHERE email = ?";
    private static final String UPDATE_USER = "UPDATE user SET email = ?, password = ? WHERE id = ?";

    @Override
    public void addUser(User user) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_IN_DB);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.execute();
            logger.info("User with email " + user.getEmail() + " added in DB.");
        } catch (SQLException e) {
            logger.error("User with email " + user.getEmail() + " can't be added in DB.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS_FROM_DB);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                users.add(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("All users can't be taken from DB.");
        }
        return users;
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = DBConnector.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_DB);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.execute();
            logger.info("User with id " + user.getId() + " removed in db.");
        } catch (SQLException e) {
            logger.error("User with id " + user.getId() + " is not found in db for deleting.");
        }
    }

    @Override
    public Optional<User> getById(Long userId) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_FROM_DB);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("User with id " + userId + " is not found in db.");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL_FROM_DB);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.error("User with email " + email + " is not found in db.");
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            String sql = String.format(Locale.US, UPDATE_USER,
                    user.getEmail(), user.getPassword(), user.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info(user + "was update in db.");
        } catch (SQLException e) {
            logger.error(user + " can't be updated in db.");
        }
    }

}
