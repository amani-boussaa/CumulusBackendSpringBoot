package com.example.cumulusspringboot.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //base donnee
    Long blog_id;
    String title;
    String author;
    LocalDate date_created = LocalDate.now(); // Set date_created to current date
    private String description;
    private String keywords;

    @Lob
    private String content;

    //@Lob
    //private byte[] imagePath;

    private String imagePath;


    @ManyToOne
    private User user;



    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<React> reacts = new ArrayList<>();
}

//les attributs & relations