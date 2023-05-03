package com.example.cumulusspringboot.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //base donnee
    Long blog_id;
    String title;
    String author;
    LocalDate date_created;
    private String description;
    private String keywords;
    private String content;

    @ManyToOne
    Blog User;
}

//les attributs & relations