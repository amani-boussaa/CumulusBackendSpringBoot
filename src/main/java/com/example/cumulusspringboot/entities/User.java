package com.example.cumulusspringboot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String prenom;
    LocalDate dateNaissance;
    String ville;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "threadCreator",fetch=FetchType.LAZY)
    List<Thread> createdThreads;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "threadA")
    List<ThreadUser> savedThreads;

    @OneToMany(mappedBy = "Auser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserActivity> activities = new ArrayList<>();
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", ville='" + ville + '\'' +
                '}';
    }
}
