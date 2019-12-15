package com.MysticSanta.Service;

import com.MysticSanta.Domain.User;

import java.util.List;

public interface UserService {
    void addNewUser(User user);

    User getUser(String id);

    List<User> getAllUser();
}
