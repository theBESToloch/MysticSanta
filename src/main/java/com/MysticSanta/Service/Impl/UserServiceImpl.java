package com.MysticSanta.Service.Impl;

import com.MysticSanta.Dao.UserDao;
import com.MysticSanta.Domain.User;
import com.MysticSanta.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public void addNewUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(String id) {
        return userDao.getUser(id);
    }
}