package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepo extends JpaRepository<Thread,Long> {
}
