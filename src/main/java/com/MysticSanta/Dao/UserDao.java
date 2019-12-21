package com.MysticSanta.Dao;

import com.MysticSanta.Domain.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    User getUser(String id);

    List<User> getAllUsers();
}
