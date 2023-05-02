package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.BadWords;
import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;

import java.util.List;

public interface BadWordsService {


    List<BadWords> findAllBadWords() throws NoChatExistsInTheRepository;
    boolean containsBadWord(Message message) throws NoChatExistsInTheRepository;

}
