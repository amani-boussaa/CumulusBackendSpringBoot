package com.example.cumulusspringboot.interfaces;


import com.example.cumulusspringboot.payload.LoginDto;
import com.example.cumulusspringboot.payload.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    public boolean verifyUser(String token);
}
