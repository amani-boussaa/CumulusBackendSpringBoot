package com.example.cumulusspringboot.services.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Message;
import com.example.cumulusspringboot.exception.oubaid.NoChatExistsInTheRepository;
import com.example.cumulusspringboot.repositories.oubaid.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessagesServiceImpl implements MessagesService {

    private static final Logger logger = LoggerFactory.getLogger(MessagesServiceImpl.class);
    @Autowired
    private MessagesRepo messagesRepo;

    @Override
    public List<Message> findallMessages() throws NoChatExistsInTheRepository {
        if (messagesRepo.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return messagesRepo.findAll();
        }
    }

    @Override
    public Map<String, Integer> getMostCommonKeywords() {
        logger.info("Starting getMostCommonKeywords() method...");
        List<String> replyMessages = messagesRepo.getAllReplyMessages();
        Map<String, Integer> keywordCount = new HashMap<>();
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        try (InputStream modelIn = getClass().getResourceAsStream("/en-pos-maxent.bin")) {
            POSModel model = new POSModel(modelIn);
            POSTaggerME tagger = new POSTaggerME(model);
            for (String replyMessage : replyMessages) {
                String[] tokens = tokenizer.tokenize(replyMessage);
                String[] tags = tagger.tag(tokens);
                for (int i = 0; i < tokens.length; i++) {
                    // Check if the token is a noun or an adjective
                    if (tags[i].startsWith("N") || tags[i].startsWith("JJ")) {
                        String keyword = tokens[i].toLowerCase();
                        keywordCount.put(keyword, keywordCount.getOrDefault(keyword, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Sort the keywords by count in descending order
        return keywordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public double getAverageMessagesPerHour() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.HOUR_OF_DAY, -1);
        Date oneHourAgo = cal.getTime();
        List<Message> messages = messagesRepo.findByTimeGreaterThan(oneHourAgo);
        return (double) messages.size() / 1.0;
    }

    @Override
    public double getAverageMessagesPerWeek() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        Date oneWeekAgo = cal.getTime();
        List<Message> messages = messagesRepo.findByTimeGreaterThan(oneWeekAgo);
        return (double) messages.size() / 7.0;
    }

    @Override
    public double getAverageMessagesPerMonth() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MONTH, -1);
        Date oneMonthAgo = cal.getTime();
        List<Message> messages = messagesRepo.findByTimeGreaterThan(oneMonthAgo);
        return (double) messages.size() / 30.0;
    }
}
