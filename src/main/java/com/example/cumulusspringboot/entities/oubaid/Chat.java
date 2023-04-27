package com.example.cumulusspringboot.entities.oubaid;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chats")
public class Chat {

    @Transient
    public static final String SEQUENCE_NAME = "chat_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatId;

    @Column(name = "first_user_name")
    private String firstUserName;

    @Column(name = "second_user_name")
    private String secondUserName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private List<Message> messageList;

    public Chat() {
    }

    public Chat(int chatId, String firstUserName, String secondUserName, List<Message> messageList) {
        this.chatId = chatId;
        this.firstUserName = firstUserName;
        this.secondUserName = secondUserName;
        this.messageList = messageList;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getFirstUserName() {
        return firstUserName;
    }

    public void setFirstUserName(String firstUserName) {
        this.firstUserName = firstUserName;
    }

    public String getSecondUserName() {
        return secondUserName;
    }

    public void setSecondUserName(String secondUserName) {
        this.secondUserName = secondUserName;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
