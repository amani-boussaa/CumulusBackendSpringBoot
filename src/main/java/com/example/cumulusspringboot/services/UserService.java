package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService  implements IUserService {
    UserRepository userRepo;
    @Override
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }
}
