package com.example.cumulusspringboot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Comment implements Serializable {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 long id;
 String content;



 @ManyToOne
  Thread commentedThread;

 @ManyToOne(cascade = CascadeType.ALL)
  User user;


 @Override
 public String toString() {
  return "Comment{" +
          "id=" + id +
          ", content='" + content + '\'' +

          ", user=" + user +
          '}';
 }
}
