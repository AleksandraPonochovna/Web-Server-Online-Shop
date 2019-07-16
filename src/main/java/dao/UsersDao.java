package dao;

import model.Product;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UsersDao {

    void addUser(User user);

    void addUser(Long id, String email, String password);

    void addUser(Long id, String email, String password, String role);

    List<User> getAllUsers();

    void deleteUser(Long id);

    Optional<User> getById(Long id);

    boolean userIsExist(String email, String password);

    Optional<String> getRoleByEmailPassword(String email, String password);

    Optional<User> getByEmail(String email);

    void addProductInBasket(User user, Product product);

}
