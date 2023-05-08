package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.interfaces.IcommentService;
import com.example.cumulusspringboot.repositories.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements IcommentService {
    @Autowired
    CommentRepo commentRepo;
    @Override
    public Comment createComment(Comment comment) {
        return commentRepo.save(comment);
    }


}
