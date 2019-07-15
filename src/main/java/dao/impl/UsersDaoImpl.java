package dao.impl;

import dao.UsersDao;
import database.Database;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UsersDaoImpl implements UsersDao {

    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class);

    @Override
    public void addUser(User user) {
        Database.users.add(user);
        logger.info("User " + user + "added in DB");
    }

    @Override
    public void addUser(Long id, String email, String password) {
        User user = new User(id, email, password);
        Database.users.add(user);
        logger.info("User " + user + "added in DB");
    }

    @Override
    public void addUser(Long id, String email, String password, String role) {
        User user = new User(id, email, password, role);
        Database.users.add(user);
        logger.info("User " + user + "added in DB");
    }

    @Override
    public List<User> getAllUsers() {
        return Database.users;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optUser = getById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            Database.users.remove(user);
            logger.info("User with email " + user.getEmail() + " removed in db");
        } else {
            logger.info("User with id " + id + " for deleting not found in db");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        List<User> users = getAllUsers();
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean userIsExist(String email, String password) {
        List<User> users = getAllUsers();
        Optional<User> user = users.stream()
                .filter(x -> x.getEmail().equals(email) && x.getPassword().equals(password))
                .findFirst();
        return user.isPresent();
    }

    @Override
    public Optional<String> getRoleByEmailPassword(String email, String password) {
        List<User> users = getAllUsers();
        return users.stream()
                .filter(x -> x.getEmail().equals(email) && x.getPassword().equals(password))
                .map(User::getRole)
                .findFirst();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        List<User> users = getAllUsers();
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

}
