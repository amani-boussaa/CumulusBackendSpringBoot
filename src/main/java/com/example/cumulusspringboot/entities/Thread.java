package com.example.cumulusspringboot.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Thread implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String content;





    @ManyToOne
    User threadCreator;

    @OneToMany(mappedBy = "userA")
    List<ThreadUser> userss;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentedThread", fetch = FetchType.LAZY)
    List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    List<ThreadTag> threadTags = new ArrayList<>();


    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }

    public List<Comment> getComments() {
        return comments;
    }
}
