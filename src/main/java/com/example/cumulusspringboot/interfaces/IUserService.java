package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.User;

import java.util.List;

public interface IUserService {
    User register(User user);

    String login(String username, String password);

    User getUserById(Long id);
}
