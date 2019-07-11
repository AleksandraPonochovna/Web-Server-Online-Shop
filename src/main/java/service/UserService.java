package service;

import model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void addUser(Long id, String email, String password);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User getById(Long id);
}
