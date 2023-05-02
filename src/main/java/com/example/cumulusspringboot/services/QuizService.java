package com.example.cumulusspringboot.services;
import com.example.cumulusspringboot.entities.Question;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.interfaces.IQuizService;
import com.example.cumulusspringboot.repositories.QuizRepo;
import com.example.cumulusspringboot.repositories.UserRepo;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.repositories.CertifRepo;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService   implements IQuizService {
    QuizRepo quizRepo;
    @Override
    public List<Quiz> getAllQuizs() {
        return quizRepo.findAll();
    }
    public Quiz getQuizById(Long id) {
        return quizRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No quiz found with id " + id));
    }

}
