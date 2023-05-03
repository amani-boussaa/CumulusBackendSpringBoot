package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.interfaces.IThreadService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/thread")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ThreadController {

    IThreadService ithreadService;

    @PostMapping("/createThread")
    public Thread createThread(@RequestBody Thread thread) {

        return ithreadService.createThread(thread);
    } ;

    @PostMapping("/createThreadWithTags")
    public Thread createThreadWithTags(@RequestBody Thread thread, @RequestParam("tags")List<String> tagNames) {

        return ithreadService.createThreadWithTags(thread,  tagNames);
    } ;

    @GetMapping("/getAllThreads")
    public List<Thread> getAllThreads() {
        return ithreadService.getAllThreads();
    }

    @GetMapping("/test")
    public List<Thread> test() {
        return ithreadService.getAllThreads();
    }

    @GetMapping("/getThreadById/{id}")
    public Thread getThreadById(@PathVariable("id") Long threadId) {

        return ithreadService.getAllComments(threadId);
    }
    @GetMapping("/testing/{threadId}/{userId}")
    public String createT(@PathVariable long threadId,@PathVariable long userId) {
        ithreadService.viewThread(threadId,userId);

        return "done?";
    };

    @PostMapping("/addComment/{id}")
    public Thread addComment(@PathVariable long id,@RequestBody Comment comment ) {

        return ithreadService.addCommentToThread(id,comment);
    } ;
    @PostMapping("/addThreadTag/{id}")
    public Thread addTagToThread(@PathVariable long id,@RequestBody List<ThreadTag> threadTag ) {
        System.out.println(threadTag);
        return ithreadService.addTagToThread(id,threadTag);
    } ;
}
