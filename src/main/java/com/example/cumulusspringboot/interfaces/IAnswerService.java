package com.example.cumulusspringboot.interfaces;
import com.example.cumulusspringboot.entities.Answer;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Quiz;

import java.util.List;
public interface IAnswerService {
    List<Answer> getAllAnswers();

    Answer getAnswerById(Long id);
}


