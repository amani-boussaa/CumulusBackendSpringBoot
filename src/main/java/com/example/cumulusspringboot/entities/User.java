package com.example.cumulusspringboot.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String username;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    String verificationToken;
    Boolean verified;
    Date expiryDate;
    String institution;
    String description;
    String address;
    @Lob
    private byte[] imagePath;
    private String phonenumber;

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "threadA")
    List<ThreadUser> savedThreads;

    @OneToMany(mappedBy = "Auser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserActivity> activities ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Wallet wallet;

    public User(long i) {
        this.id=i;
    }
}
