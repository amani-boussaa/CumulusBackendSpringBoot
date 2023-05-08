package com.example.cumulusspringboot.controllers;


import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.AuthService;
import com.example.cumulusspringboot.payload.JWTAuthResponse;
import com.example.cumulusspringboot.payload.LoginDto;
import com.example.cumulusspringboot.payload.RegisterDto;
import com.example.cumulusspringboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/v1/auth")
//@AllArgsConstructor
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Value("${app-jwt-expiration-milliseconds}")
    private Long jwtExpirationMs;
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        Long expires = System.currentTimeMillis() + jwtExpirationMs;
        jwtAuthResponse.setExpires(expires);
        jwtAuthResponse.setUser(userRepository.getUserByEmail(loginDto.getEmail()));


        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", response);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
//        String response = authService.register(registerDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

@GetMapping("/verify")
public String verifyUser(@RequestParam("token") String token) {
    boolean result = authService.verifyUser(token);
    if (result) {
        return "verification-successful";
    } else {
        return "verification-failed";
    }
}

}