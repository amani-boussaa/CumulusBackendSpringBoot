package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Question;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.requests.assignCertifToUserReq;
import com.example.cumulusspringboot.services.CertifService;
import com.example.cumulusspringboot.services.QuestionService;
import com.example.cumulusspringboot.services.QuizService;
import com.example.cumulusspringboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/getAllQuests")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
