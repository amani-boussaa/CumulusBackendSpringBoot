package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByuserId(Long userID);
}