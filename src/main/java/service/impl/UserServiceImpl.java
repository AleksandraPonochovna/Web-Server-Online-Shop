package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import factory.hibernate.UserDaoHibernateFactory;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoHibernateFactory.getUserDao();

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public Optional<User> getById(Long userId) {
        return userDao.getById(userId);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
