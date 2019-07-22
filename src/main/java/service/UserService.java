package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    void addUser(String email, String password, String role);

    List<User> getAllUsers();

    void deleteUser(Long id);

    Optional<User> getById(Long id);

    boolean userIsExist(String email, String password);

    Optional<String> getRoleByEmailPassword(String email, String password);

    Optional<User> getByEmail(String email);

    void editUser(User user, String newEmail, String newPassword);

}
