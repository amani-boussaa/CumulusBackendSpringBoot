package com.example.cumulusspringboot.payload;

import com.example.cumulusspringboot.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phonenumber;
    private Role role;
    private String institution;
    private String description;
    private String address;
}
