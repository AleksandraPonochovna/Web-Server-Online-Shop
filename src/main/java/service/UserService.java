package service;

import model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User getById(Long id);
}
