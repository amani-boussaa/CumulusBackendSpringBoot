package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Chat;

import java.util.List;
public interface IChatService {
    Chat createChat(Chat chat);
    Chat getChatById(Long chatId);
    List<Chat> getAllChats();
    void deleteChat(Long chatId);
}