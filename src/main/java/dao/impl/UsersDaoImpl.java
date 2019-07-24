package dao.impl;

import dao.UsersDao;
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

public class UsersDaoImpl implements UsersDao {

    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class);
    private static final String ADD_USER_IN_DB = "INSERT INTO users (email, password, role) " +
            "VALUES (?, ?, ?)";
    private static final String GET_ALL_USERS_FROM_DB = "SELECT * FROM users";
    private static final String DELETE_USER_FROM_DB = "DELETE FROM users WHERE id = ?";
    private static final String GET_USER_BY_ID_FROM_DB = "SELECT * FROM users WHERE id = ?";
    private static final String GET_USER_BY_EMAIL_FROM_DB = "SELECT * FROM users WHERE email = ?";
    private static final String GET_ROLE_BY_EMAIL_PASSWORD_FROM_DB = "SELECT role FROM users " +
            "WHERE email = ? AND password = ?";
    private static final String IS_EXIST_USER_IN_DB = "SELECT * FROM users WHERE email = ? AND " +
            "password = ?";
    private static final String EDIT_USER = "UPDATE users SET email = ?, password = ? WHERE id = ?";

    @Override
    public void addUser(User user) {
        addUser(user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public void addUser(String email, String password, String role) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_IN_DB);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.execute();
            logger.info("User with email " + email + " added in DB.");
        } catch (SQLException e) {
            logger.error("User with email " + email + " can't be added in DB.");
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
    public void deleteUser(Long id) {
        try (Connection connection = DBConnector.connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_DB);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("User with id " + id + " removed in db.");
        } catch (SQLException e) {
            logger.error("User with id " + id + " is not found in db for deleting.");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_FROM_DB);
            preparedStatement.setLong(1, id);
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
            logger.error("User with id " + id + " is not found in db.");
        }
        return Optional.empty();
    }

    @Override
    public boolean userIsExist(String email, String password) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_USER_IN_DB);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("User with email " + email + " is not exist in db.");
        }
        return false;
    }

    @Override
    public Optional<String> getRoleByEmailPassword(String email, String password) {
        try (Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_BY_EMAIL_PASSWORD_FROM_DB);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                return Optional.of(role);
            }
        } catch (SQLException e) {
            logger.error("User with email " + email + " is not found in db.");
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
    public void editUser(User user, String newEmail, String newPassword) {
        try(Connection connection = DBConnector.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setLong(3, user.getId());
            String sql = String.format(Locale.US, EDIT_USER, newEmail, newPassword, user.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info(user + "was edit in db.");
        } catch (SQLException e) {
            logger.error(user + " can't be edit in db.");
        }
    }

}
