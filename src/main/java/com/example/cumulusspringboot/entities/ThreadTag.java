package com.example.cumulusspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ThreadTag implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  id;

    String name ;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    List<Thread> threadT = new ArrayList<>(); ;


    public ThreadTag(String tagName) {
        this.name=tagName;
    }
}
