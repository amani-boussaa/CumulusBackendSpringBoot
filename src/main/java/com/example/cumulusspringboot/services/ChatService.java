package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Chat;
import com.example.cumulusspringboot.interfaces.IChatService;
import com.example.cumulusspringboot.repositories.ChatRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChatService implements IChatService {
    private ChatRepo chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public void deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
    }
}
