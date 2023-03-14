package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Relation;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByUsername(String username);

    User findByToken(String token);

}
