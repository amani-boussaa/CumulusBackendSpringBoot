package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.BadWords;

import java.util.List;

public interface BadWordsService {


    List<BadWords> findAllBadWords();

    BadWords addBadWord(BadWords badWord);

    void deleteBadWord(Long id);
}
