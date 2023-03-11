package com.example.cumulusspringboot.entities;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String username;
    String password;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;



}
