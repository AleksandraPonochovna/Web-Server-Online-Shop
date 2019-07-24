package dao.impl;

import dao.UsersDao;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnector;

import java.sql.Connection;
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
            "VALUES ('%s', '%s', '%s')";
    private static final String GET_ALL_USERS_FROM_DB = "SELECT * FROM users";
    private static final String DELETE_USER_FROM_DB = "DELETE FROM users WHERE id = %d";
    private static final String GET_USER_BY_ID_FROM_DB = "SELECT * FROM users WHERE id = %d";
    private static final String GET_USER_BY_EMAIL_FROM_DB = "SELECT * FROM users WHERE email = '%s'";
    private static final String GET_ROLE_BY_EMAIL_PASSWORD_FROM_DB = "SELECT role FROM users " +
            "WHERE email = '%s' AND password = '%s'";
    private static final String IS_EXIST_USER_IN_DB = "SELECT * FROM users WHERE email = '%s' " +
            "AND password = '%s'";
    private static final String EDIT_USER = "UPDATE users SET email = '%s', password = '%s'" +
            " WHERE id = %d";

    @Override
    public void addUser(User user) {
        addUser(user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public void addUser(String email, String password, String role) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(ADD_USER_IN_DB, email, password, role);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("User with email " + email + " added in DB.");
        } catch (SQLException e) {
            logger.info("User with email " + email + " can't be added in DB.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_FROM_DB);
            while (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                users.add(userFromDb);
            }
        } catch (SQLException e) {
            logger.info("Users can't be taken from DB.");
        }
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        try (Connection connection = DBConnector.connect()){
            String sql = String.format(Locale.US, DELETE_USER_FROM_DB, id);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info("User with id " + id + " removed in db.");
        } catch (SQLException e) {
            logger.info("User with id " + id + " is not found in db for deleting.");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(Locale.US, GET_USER_BY_ID_FROM_DB, id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.info("User with id " + id + " is not found in db.");
        }
        return Optional.empty();
    }

    @Override
    public boolean userIsExist(String email, String password) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(IS_EXIST_USER_IN_DB, email, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.info("User with email " + email + " is not exist in db.");
        }
        return false;
    }

    @Override
    public Optional<String> getRoleByEmailPassword(String email, String password) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(GET_ROLE_BY_EMAIL_PASSWORD_FROM_DB, email, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                return Optional.of(role);
            }
        } catch (SQLException e) {
            logger.info("User with email " + email + " is not found in db.");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (Connection connection = DBConnector.connect()) {
            String sql = String.format(GET_USER_BY_EMAIL_FROM_DB, email);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User userFromDb = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                return Optional.of(userFromDb);
            }
        } catch (SQLException e) {
            logger.info("User with email " + email + " is not found in db.");
        }
        return Optional.empty();
    }

    @Override
    public void editUser(User user, String newEmail, String newPassword) {
        try(Connection connection = DBConnector.connect()) {
            String sql = String.format(Locale.US, EDIT_USER, newEmail, newPassword, user.getId());
            Statement statement = connection.createStatement();
            statement.execute(sql);
            logger.info(user + "was edit in db.");
        } catch (SQLException e) {
            logger.info(user + " can't be edit in db.");
        }
    }

}
