package com.example.cumulusspringboot.services;
import com.example.cumulusspringboot.entities.Answer;
import com.example.cumulusspringboot.entities.Question;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.interfaces.IAnswerService;
import com.example.cumulusspringboot.interfaces.IQuizService;
import com.example.cumulusspringboot.repositories.AnswerRepo;
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
public class AnswerService   implements IAnswerService {
    AnswerRepo answerRepo;
    @Override
    public List<Answer> getAllAnswers() {
        return answerRepo.findAll();
    }

    public Answer getAnswerById(Long id) {
        return answerRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No answer found with id " + id));
    }

}
