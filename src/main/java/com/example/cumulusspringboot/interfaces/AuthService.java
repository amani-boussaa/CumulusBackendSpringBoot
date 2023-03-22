package com.example.cumulusspringboot.interfaces;


import com.example.cumulusspringboot.payload.LoginDto;
import com.example.cumulusspringboot.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
