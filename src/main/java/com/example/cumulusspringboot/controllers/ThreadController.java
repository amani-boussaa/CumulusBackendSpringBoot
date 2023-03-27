package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IThreadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thread")
@AllArgsConstructor
public class ThreadController {

    IThreadService threadService;

    @PostMapping("/createThread")
    public Thread createThread(@RequestBody Thread thread){

        return threadService.createThread(thread);
    };

    @GetMapping("/getAllThreads")
    public List<Thread> getAllThreads(){
        return threadService.getAllThreads();
    }
    @GetMapping("/test")
    public String test(){
        return "Works ?";
    }
}
