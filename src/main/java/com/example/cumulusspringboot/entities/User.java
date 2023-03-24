package com.example.cumulusspringboot.entities;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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


}
