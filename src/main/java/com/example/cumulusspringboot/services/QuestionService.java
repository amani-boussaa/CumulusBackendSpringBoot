package com.example.cumulusspringboot.services;
import com.example.cumulusspringboot.entities.Question;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.interfaces.IQuestionService;
import com.example.cumulusspringboot.interfaces.IQuizService;
import com.example.cumulusspringboot.repositories.QuestionRepo;
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
public class QuestionService   implements IQuestionService {
    QuestionRepo questionRepo;
    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No question found with id " + id));
    }



}
