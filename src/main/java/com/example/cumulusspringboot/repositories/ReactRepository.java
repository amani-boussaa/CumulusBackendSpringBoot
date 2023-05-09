package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.React;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactRepository extends JpaRepository<React, Integer> {
}