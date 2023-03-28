package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface IUserService {
    ResponseEntity<List<User>> getAllUsers();


     ResponseEntity<User> updateUser(long id, User user);

    ResponseEntity<HttpStatus> removeUser(Long id);

    User retrieveUser(Long idUser);
    User findUserByEmail(String email);
    public String uploadFile(MultipartFile file) throws IOException;
    public ResponseEntity<?> uploadImage( Long id, MultipartFile file) throws IOException;
}
