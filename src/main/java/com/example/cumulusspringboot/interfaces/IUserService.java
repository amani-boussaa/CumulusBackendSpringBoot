package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.User;

import javax.servlet.http.HttpServletRequest;


public interface IUserService {

    User register(User user);

    String login(String username, String password);

    User getUserById(Long id);
    public boolean verifyUser(String token) ;

    }
