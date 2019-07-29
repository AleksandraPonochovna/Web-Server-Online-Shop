package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(User user);

    Optional<User> getById(Long userId);

    Optional<User> getByEmail(String email);

    void updateUser(User user);

}
