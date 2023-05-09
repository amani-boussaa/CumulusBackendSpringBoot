package com.example.cumulusspringboot.exception.oubaid;

public class ChatAlreadyExistsException extends RuntimeException {
    public ChatAlreadyExistsException() {
        super("Chat with the same participants already exists.");
    }
}
