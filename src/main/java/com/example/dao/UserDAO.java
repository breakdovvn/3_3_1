package com.example.dao;

import com.example.model.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    void update(User user);
    void delete(Long id);
    User getUserById(Long id);
    List<User> listUsers();
}

