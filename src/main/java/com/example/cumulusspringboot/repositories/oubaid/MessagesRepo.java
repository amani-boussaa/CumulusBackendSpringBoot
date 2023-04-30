package com.example.cumulusspringboot.repositories.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepo extends JpaRepository<Message, Long> {
    // Custom method to retrieve all reply messages
    @Query("SELECT m.replymessage FROM Message m")
    List<String> getAllReplyMessages();

}
