package com.example.cumulusspringboot.services.oubaid;

// BadWordsServiceImpl.java

import java.util.List;

import com.example.cumulusspringboot.entities.oubaid.BadWords;
import com.example.cumulusspringboot.repositories.oubaid.BadWordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadWordsServiceImpl implements BadWordsService {

    @Autowired
    private BadWordsRepo badWordsRepository;

    @Override
    public List<BadWords> findAllBadWords() {
        return badWordsRepository.findAll();
    }


    @Override
    public BadWords addBadWord(BadWords badWord) {
        return badWordsRepository.save(badWord);
    }

    @Override
    public void deleteBadWord(Long id) {
        badWordsRepository.deleteById(id);
    }
}

