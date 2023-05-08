package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.exception.ResourceNotFoundException;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api2/v1/users")
@AllArgsConstructor
public class UserController {
    IUserService userService;

UserRepository userRepository;
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

    @GetMapping("/retrieveUser/{id}")
    public User retrieveUser(@PathVariable ("id") long idUser) {
        return userService.retrieveUser(idUser);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return userService.uploadFile(file);
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return userService.uploadImage(id,file);
    }
    @GetMapping("/getblobimage/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        byte[] imagePath = user.getImagePath();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imagePath, headers, HttpStatus.OK);

    }
}