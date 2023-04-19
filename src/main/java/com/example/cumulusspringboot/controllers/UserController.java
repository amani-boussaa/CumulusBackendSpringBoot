package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    IUserService userservice;
    @GetMapping("/retrieveAllUsers")
    public List<User> retrieveAllUsers() {
        return userservice.retrieveAllUsers();
    }
}
