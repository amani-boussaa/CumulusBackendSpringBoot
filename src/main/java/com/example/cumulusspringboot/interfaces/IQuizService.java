package com.example.cumulusspringboot.interfaces;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Quiz;

import java.util.List;
public interface IQuizService {
    List<Quiz> getAllQuizs();
    Quiz getQuizById(Long id);
}
