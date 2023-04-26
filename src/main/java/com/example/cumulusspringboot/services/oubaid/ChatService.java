package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Chat;
import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.ChatAlreadyExistException;
import com.example.cumulusspringboot.exception.oubaid.ChatNotFoundException;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;

import java.util.HashSet;
import java.util.List;

public interface ChatService {

    public Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(long ChatId) throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)  throws ChatNotFoundException;

    Chat addMessage(Message add, long ChatId) throws ChatNotFoundException;
}
