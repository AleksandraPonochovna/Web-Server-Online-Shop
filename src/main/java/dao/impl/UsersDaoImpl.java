package dao.impl;

import dao.UsersDao;
import database.Database;
import model.User;

import java.util.List;

public class UsersDaoImpl implements UsersDao {

    @Override
    public void addUser(User user) {
        Database.users.add(user);
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
    }

}
