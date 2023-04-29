package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;
import com.example.cumulusspringboot.repositories.oubaid.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepo messagesRepo;
    @Override
    public List<Message> findallMessages() throws NoChatExistsInTheRepository {
        if (messagesRepo.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return messagesRepo.findAll();
        }

    }
}
