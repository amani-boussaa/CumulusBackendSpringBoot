package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;

import java.util.List;

public interface MessagesService {

    List<Message> findallMessages() throws NoChatExistsInTheRepository;
}
