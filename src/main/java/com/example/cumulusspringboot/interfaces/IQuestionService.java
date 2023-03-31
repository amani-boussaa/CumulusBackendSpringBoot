package com.example.cumulusspringboot.interfaces;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Question;
import com.example.cumulusspringboot.entities.Quiz;

import java.util.List;
public interface IQuestionService {
    List<Question> getAllQuestions();

    Question getQuestionById(Long id);
}
