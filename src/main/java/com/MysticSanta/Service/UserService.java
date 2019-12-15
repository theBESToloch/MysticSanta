package com.MysticSanta.Service;

import com.MysticSanta.Domain.User;

public interface UserService {
    void addNewUser(User user);

    User getUser(String id);
}
