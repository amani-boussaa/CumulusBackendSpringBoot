package com.example.cumulusspringboot.entities.oubaid;

import javax.persistence.*;

@Entity
@Table(name = "bad_words")
public class BadWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    public BadWords() {}

    public BadWords(String word) {
        this.word = word;
    }

    // getters and setters
}
