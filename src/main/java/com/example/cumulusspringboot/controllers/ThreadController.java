package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IThreadService;
import com.example.cumulusspringboot.interfaces.IUserActivityService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/thread")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ThreadController {

    IThreadService ithreadService;

    IUserActivityService iUserActivityService;
    @GetMapping("/test")
    public String testtt() {

        return "dawdawdaw";
    } ;



    @PostMapping("/createThread/{userID}")
    public Thread createThread(@RequestBody Thread thread,@PathVariable Long userID) {
        return ithreadService.createThread(thread,userID);
    } ;

    @PostMapping("/createThreadWithTags")
    public Thread createThreadWithTags(@RequestBody Thread thread, @RequestParam("tags")List<String> tagNames) {
        System.out.println(thread + tagNames.toString());
        return ithreadService.createThreadWithTags(thread,  tagNames);
    } ;

    @GetMapping("/getAllThreads")
    public List<Thread> getAllThreads() {
        return ithreadService.getAllThreads();
    }
    @GetMapping("/getThreadByUser/{userID}")
    public List<Thread> getThreadByUser(@PathVariable Long userID) {
        return ithreadService.getThreadByUser(userID);
    }


    @GetMapping("/getThreadStats/{userid}")
    public ArrayList threadStats(@PathVariable Long userid) {
        ArrayList data =  ithreadService.ThreadStats(userid);
        System.out.println(data);
        return data;
    } ;


    @GetMapping("/testt")
    public List<Thread> test() {
        return ithreadService.getAllThreads();
    }

    @GetMapping("/getThreadById/{id}")
    public Thread getThreadById(@PathVariable("id") Long threadId) {

        return ithreadService.getAllComments(threadId);
    }
    @GetMapping("/viewThread/{threadId}/{userId}")
    public int viewThread(@PathVariable long userId ,@PathVariable long threadId) {


         iUserActivityService.updateViewCount(userId,threadId);
return 0;

    };

    @GetMapping("/recommended/{userId}")
    public List<Thread> recommended(@PathVariable long userId ) {
        List<ThreadTag> tags = ithreadService.getMostViewedThreadTagsForUser(userId, 5);
        List<Thread> recommendedThreads = ithreadService.getRecommendedThreads(tags, 10);

        return recommendedThreads;
    }

    @PostMapping("/addComment/{id}/{userid}")
    public Thread addComment(@PathVariable long id,@PathVariable long userid,@RequestBody Comment comment ) {

        return ithreadService.addCommentToThread(id,comment,userid);
    } ;
    @PostMapping("/addThreadTag/{id}")
    public Thread addTagToThread(@PathVariable long id,@RequestBody List<ThreadTag> threadTag ) {
        System.out.println(threadTag);
        return ithreadService.addTagToThread(id,threadTag);
    } ;
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteThread(@PathVariable Long id) {
        try {
            ithreadService.deleteThread(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
