package service.impl;

import dao.UsersDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UsersDao usersDao = UserDaoFactory.getUserDao();

    @Override
    public void addUser(Long id, String email, String password) {
        usersDao.addUser(id, email, password);
    }

    @Override
    public void addUser(Long id, String email, String password, String role) {
        usersDao.addUser(id, email, password, role);
    }

    @Override
    public void addUser(User user) {
        usersDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        usersDao.deleteUser(id);
    }

    @Override
    public Optional<User> getById(Long id) {
        return usersDao.getById(id);
    }

    @Override
    public boolean userIsExist(String email, String password) {
        return usersDao.userIsExist(email, password);
    }

    @Override
    public Optional<String> getRoleByEmailPassword(String email, String password) {
        return usersDao.getRoleByEmailPassword(email, password);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return usersDao.getByEmail(email);
    }

}
