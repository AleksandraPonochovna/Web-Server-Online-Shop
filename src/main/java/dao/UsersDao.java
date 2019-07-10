package dao;

import model.User;

import java.util.List;

public interface UsersDao {

    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

}
