package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.requests.assignCertifToUserReq;
import com.example.cumulusspringboot.services.CertifService;
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
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;
    @GetMapping("/getAllQuizs")
    public List<Quiz> getAllQuizs() {
        return quizService.getAllQuizs();
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }
}
