package com.example.cumulusspringboot.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment implements Serializable {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 long id;
 String content;


 @ManyToOne
  Thread commentedThread;

 @ManyToOne
  User user;

}