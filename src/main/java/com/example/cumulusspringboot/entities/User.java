package com.example.cumulusspringboot.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String prenom;
    LocalDate dateNaissance;
    String ville;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "threadCreator")
    List<Thread> createdThreads;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "threadA")
    List<ThreadUser> savedThreads;
}
