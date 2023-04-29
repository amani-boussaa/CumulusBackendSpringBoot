package com.example.cumulusspringboot.entities.oubaid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bad_words")
@Getter
@Setter
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

}
