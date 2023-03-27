package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepo extends JpaRepository<Thread,Long> {
}
