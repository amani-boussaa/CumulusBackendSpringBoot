package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IcommentService    {
    Comment createComment(Comment comment);


}
