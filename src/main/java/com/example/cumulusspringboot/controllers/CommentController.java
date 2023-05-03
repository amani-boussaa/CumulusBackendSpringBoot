package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.interfaces.IBlogService;
import com.example.cumulusspringboot.interfaces.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private IComment iComment;

    @PostMapping("/CreateComment")
    public Comment CreateComment(@RequestBody Comment C) {
        return iComment.CreateComment(C);
    }

    @GetMapping("/ReadComment")
    public List<Comment> ReadComment() {
        return iComment.ReadComment();
    }

    @PutMapping("/UpdateComment")
    public Comment UpdateComment(@RequestBody Comment C) {
        return iComment.UpdateComment(C);
    }

    @DeleteMapping("/DeleteComment/{id}")
    public void DeleteComment(@PathVariable Long id) {
        iComment.DeleteComment(id);
    }
}
