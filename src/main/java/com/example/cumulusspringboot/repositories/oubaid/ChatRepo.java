package com.example.cumulusspringboot.repositories.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface ChatRepo extends JpaRepository<Chat, Long> {
    HashSet<Chat> getChatByFirstUserName(String username);

    HashSet<Chat> getChatBySecondUserName(String username);

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName);

    HashSet<Chat> getChatBySecondUserNameAndFirstUserName(String firstUserName, String secondUserName);
}
