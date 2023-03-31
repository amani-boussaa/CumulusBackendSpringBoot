package com.example.cumulusspringboot.interfaces;
import com.example.cumulusspringboot.entities.User;
import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();
}
