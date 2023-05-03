package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}