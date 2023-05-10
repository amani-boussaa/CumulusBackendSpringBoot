package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadTagRepo extends JpaRepository<ThreadTag,Long> {
    @Query("SELECT t FROM ThreadTag t WHERE t.name = :name")
     ThreadTag findByName(String name);

}
