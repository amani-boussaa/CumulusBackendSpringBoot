package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IcommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    IcommentService icommentService;
    @PostMapping("/createComment")
    public Comment createComment(@RequestBody Comment comment){

        return icommentService.createComment(comment);
    };




}
