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
        Blog commentedBlog;

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
