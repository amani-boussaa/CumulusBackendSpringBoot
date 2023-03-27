package com.example.cumulusspringboot.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Thread implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String content ;

    @ManyToOne
    User threadCreator;

@OneToMany(mappedBy = "userA")
    List<ThreadUser> userss;

@OneToMany(cascade = CascadeType.ALL,mappedBy = "commentedThread")
    List<Comment> comments;
}
