package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Answer;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Quiz;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.requests.assignCertifToUserReq;
import com.example.cumulusspringboot.services.AnswerService;
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
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    @GetMapping("/getAllAnswers")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
