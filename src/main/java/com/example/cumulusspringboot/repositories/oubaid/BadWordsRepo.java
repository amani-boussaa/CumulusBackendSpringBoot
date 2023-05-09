package com.example.cumulusspringboot.repositories.oubaid;

import com.example.cumulusspringboot.entities.oubaid.BadWords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BadWordsRepo extends JpaRepository<BadWords, Long> {
}