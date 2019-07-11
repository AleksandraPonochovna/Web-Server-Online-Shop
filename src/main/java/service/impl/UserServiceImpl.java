package service.impl;

import dao.UsersDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UsersDao usersDao = UserDaoFactory.getUserDao();

    @Override
    public void addUser(Long id, String email, String password) {
        usersDao.addUser(id, email, password);
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
    public User getById(Long id) {
        return usersDao.getById(id);
    }

}
