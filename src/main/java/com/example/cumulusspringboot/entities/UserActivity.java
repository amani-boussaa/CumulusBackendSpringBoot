package com.example.cumulusspringboot.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
     User Auser;
    @ElementCollection
    @CollectionTable(name = "user_activity_tag_counts", joinColumns = @JoinColumn(name = "user_activity_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "tag_name")),
            @AttributeOverride(name = "count", column = @Column(name = "tag_count"))
    })

     List<TagCount> tagCounts;

}



