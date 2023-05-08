package com.example.cumulusspringboot.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ThreadUser implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  id;
    @ManyToOne
    User userA;
    @ManyToOne
    Thread threadA;

    boolean liked;


}
