package com.example.cumulusspringboot.repositories;
import java.util.Optional;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuizRepo extends JpaRepository<Quiz,Long> {
    Optional<Quiz> findById(Long id);
}

