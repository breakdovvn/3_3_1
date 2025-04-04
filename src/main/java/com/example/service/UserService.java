package com.example.service;

import com.example.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void update(User user);
    void delete(Long id);
    User getUserById(Long id);
    List<User> listUsers();
}
