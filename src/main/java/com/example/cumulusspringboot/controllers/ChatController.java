package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Chat;
import com.example.cumulusspringboot.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {
    private ChatService chatService;

    @GetMapping
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable("id") Long chatId) {
        return chatService.getChatById(chatId);
    }

    @PostMapping
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable("id") Long chatId) {
        chatService.deleteChat(chatId);
    }
}
