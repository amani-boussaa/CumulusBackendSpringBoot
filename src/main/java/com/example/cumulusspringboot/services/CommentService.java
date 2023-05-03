package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Blog;
import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.interfaces.IComment;
import com.example.cumulusspringboot.repositories.BlogRepository;
import com.example.cumulusspringboot.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements IComment {

    @Autowired
    private CommentRepository commentRepository;



    @Override
    public Comment CreateComment(Comment C) {

        return commentRepository.save(C);
    }

    @Override
    public List<Comment> ReadComment() {
        List<Comment> commentList = new ArrayList<>();
        commentRepository.findAll().forEach(commentList::add);
        return commentList;
    }

    @Override
    public Comment UpdateComment(Comment C) {

        return commentRepository.save(C);
    }

    @Override
    public void DeleteComment(Long id) {

        commentRepository.deleteById(id);
    }
}
