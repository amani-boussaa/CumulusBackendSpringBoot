package com.example.cumulusspringboot.interfaces.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Chat;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;

import java.util.List;

public interface IChatService {
    Chat createChat(Chat chat);
    Chat getChatById(Long chatId);
    List<Chat> getAllChats();

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    void deleteChat(Long chatId);

    void addUserToChat(Long chatId, Long userId);

    void removeUserFromChat(Long chatId, Long userId);

}
