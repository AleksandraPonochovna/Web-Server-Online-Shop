package dao;

import model.User;

import java.util.List;

public interface UsersDao {

    void addUser(User user);

    void addUser(Long id, String email, String password);

    void addUser(Long id, String email, String password, User.ROLE role);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User getById(Long id);

    boolean userIsExist(String email, String password);

    User.ROLE getRoleByEmailPassword(String email, String password);

    User getByEmail(String email);

}
