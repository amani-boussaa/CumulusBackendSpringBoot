package com.example.cumulusspringboot.controllers;


import com.example.cumulusspringboot.interfaces.IThreadTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/threadtag")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")

public class ThreadTagController {

    IThreadTagService iThreadTagService;

}
