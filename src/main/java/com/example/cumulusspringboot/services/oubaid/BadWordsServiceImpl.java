package com.example.cumulusspringboot.services.oubaid;

import java.util.List;

import com.example.cumulusspringboot.entities.oubaid.BadWords;
import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;
import com.example.cumulusspringboot.repositories.oubaid.BadWordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadWordsServiceImpl implements BadWordsService {

    @Autowired
    private BadWordsRepo badWordsRepository;

    @Override
    public List<BadWords> findAllBadWords() throws NoChatExistsInTheRepository {
       if (badWordsRepository.findAll().isEmpty()){
           throw new NoChatExistsInTheRepository();
       } else {
           return badWordsRepository.findAll();
       }
    }
    @Override
    public boolean containsBadWord(Message message) throws NoChatExistsInTheRepository {
        String textContent = message.getReplymessage();
        List<BadWords> badWords = findAllBadWords();
        for (BadWords badWord : badWords) {
            if (textContent.toLowerCase().contains(badWord.getWord().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}

