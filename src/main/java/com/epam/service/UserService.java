package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.dao.UserDao;
import com.epam.entity.User;

public class UserService {
    private static final Logger logger = LogManager.getLogger();
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(long userId) {
        logger.info("Get user by id:" + userId);
        return userDao.getUserById(userId);
    }

    public User getUserByEmail(String email) {
        logger.info("Get user by email:" + email);
        Optional<User> userOptional = userDao.getUserByEmail(email);
        return userOptional.orElse(null);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        logger.info("Get user by name:" + name + ",page size:" + pageSize + ",page number:" + pageNum);
        return userDao.getUsersByName(name, pageSize, pageNum);
    }

    public User create(User user) {
        logger.info("Input user in storage. User:" + user);
        return userDao.create(user);
    }

    public User update(User user) {
        logger.info("Update user:" + user);
        return userDao.update(user);
    }

    public boolean delete(long userId) {
        logger.info("Delete user by id:" + userId);
        return userDao.delete(userId);
    }
}
