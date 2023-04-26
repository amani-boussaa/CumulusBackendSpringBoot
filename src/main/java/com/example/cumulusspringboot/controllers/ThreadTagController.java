package com.example.cumulusspringboot.controllers;


import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.interfaces.IThreadTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threadtag")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")

public class ThreadTagController {

    IThreadTagService iThreadTagService;



    @PostMapping("/createThreadTag")
    public ThreadTag createThread(@RequestBody ThreadTag threadTag) {

        return iThreadTagService.createThreadTag(threadTag);
    } ;

    @GetMapping("/getAllThreadTags")
    public List<ThreadTag> getAllThreadTags() {

        return iThreadTagService.getAllThreadTags();
    } ;




}
