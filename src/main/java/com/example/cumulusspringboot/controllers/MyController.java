package com.example.cumulusspringboot.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class MyController {

    @GetMapping("api/home")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "this is home page");
        return response;
    }
}