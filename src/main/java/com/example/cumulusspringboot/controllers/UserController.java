package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api2/v1/users")
@AllArgsConstructor
public class UserController {
    IUserService userService;

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }



    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
       return userService.updateUser(id,user);
    }

    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable("id") long id) {
        return userService.removeUser(id);
    }

    @GetMapping("/retrieveUser")
    public User retrieveUser(@PathVariable ("id") long idUser) {
        return userService.retrieveUser(idUser);
    }
}