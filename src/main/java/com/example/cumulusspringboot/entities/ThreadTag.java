package com.example.cumulusspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ThreadTag {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name ;


    @ManyToMany(mappedBy = "threadTags")
    List<Thread> threadT = new ArrayList<>();


}
