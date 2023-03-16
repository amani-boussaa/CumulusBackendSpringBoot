package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IUserService {

    User register(User user);

    String login(String username, String password);

    User getUserById(Long id);
    public boolean verifyUser(String token) ;
    void logout(HttpServletRequest request, HttpServletResponse response);

    }
