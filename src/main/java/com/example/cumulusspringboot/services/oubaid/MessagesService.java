package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;

import java.util.List;
import java.util.Map;

public interface MessagesService {

    List<Message> findallMessages() throws NoChatExistsInTheRepository;
    Map<String, Integer> getMostCommonKeywords();

    double getAverageMessagesPerHour();

    double getAverageMessagesPerWeek();

    double getAverageMessagesPerMonth();

    Map<String, Double> getUsersHappinessToday();

    double getHappinessScore(String adjective);
}
