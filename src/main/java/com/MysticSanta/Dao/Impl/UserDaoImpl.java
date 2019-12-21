package com.MysticSanta.Dao.Impl;


import com.MysticSanta.Dao.UserDao;
import com.MysticSanta.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return users.keySet().stream().map((key) -> users.get(key)).collect(Collectors.toList());
    }


}
