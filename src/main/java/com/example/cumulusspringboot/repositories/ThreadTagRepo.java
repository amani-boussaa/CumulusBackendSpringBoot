package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.ThreadTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadTagRepo extends JpaRepository<ThreadTag,Long> {
}
