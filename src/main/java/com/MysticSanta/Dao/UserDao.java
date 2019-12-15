package com.MysticSanta.Dao;

import com.MysticSanta.Domain.User;

public interface UserDao {

    void addUser(User user);

    User getUser(String id);
}
