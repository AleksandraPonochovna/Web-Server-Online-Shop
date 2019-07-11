package dao.impl;

import dao.UsersDao;
import database.Database;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;

public class UsersDaoImpl implements UsersDao {

    private static final Logger logger = Logger.getLogger(UsersDaoImpl.class);
    private User user;

    @Override
    public void addUser(User user) {
        Database.users.add(user);
        logger.info("User " + user + "added in DB");
    }

    @Override
    public void addUser(Long id, String email, String password) {
        user = new User(id, email, password);
        Database.users.add(user);
        logger.info("User " + user + "added in DB");
    }

    @Override
    public List<User> getAllUsers() {
        return Database.users;
    }

    @Override
    public void deleteUser(Long id) {
        Database.users.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .ifPresent(x -> Database.users.remove(x));
        logger.info("User with email " + getById(id).getEmail() + "removed in db");
    }

    @Override
    public User getById(Long id) {
        for (User user : Database.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

}
