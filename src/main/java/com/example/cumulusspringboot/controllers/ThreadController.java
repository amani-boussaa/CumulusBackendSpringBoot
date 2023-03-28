package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IThreadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thread")
@AllArgsConstructor
public class ThreadController {

    IThreadService ithreadService;

    @PostMapping("/createThread")
    public Thread createThread(@RequestBody Thread thread){

        return ithreadService.createThread(thread);
    };

    @GetMapping("/getAllThreads")
    public List<Thread> getAllThreads(){
        return ithreadService.getAllThreads();
    }
    @GetMapping("/test")
    public String test(){
        return "Works ?";
    }

    @GetMapping("/getThreadById/{id}")
    public Thread getThreadById(@PathVariable("id" )Long threadId){

        return ithreadService.getAllComments(threadId);
    }
}
