package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
