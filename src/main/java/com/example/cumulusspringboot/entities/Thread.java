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
import java.util.Set;

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
public class Thread implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String content;


    @ManyToOne
    User threadCreator;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentedThread")
    List<Comment> comments;

    @ManyToMany(mappedBy = "threadT",cascade = CascadeType.ALL)
    List<ThreadTag> threadTags ;


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

    public void addComment(Comment comment) {


        comments.add(comment);
    }
    public Thread addThreadTag(ThreadTag threadTag) {


        threadTags.add(threadTag);
        return  this;
    }
}
