package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {

}
