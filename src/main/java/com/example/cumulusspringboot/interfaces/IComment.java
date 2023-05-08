package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Blog;
import com.example.cumulusspringboot.entities.Comment;

import java.util.List;

public interface IComment {
    Comment CreateComment (Comment C);

    List<Comment> ReadComment ();

    Comment UpdateComment(Comment C);

    void DeleteComment(Long id);
}
