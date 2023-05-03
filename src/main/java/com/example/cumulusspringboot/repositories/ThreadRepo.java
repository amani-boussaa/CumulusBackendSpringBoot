package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepo extends JpaRepository<Thread,Long> {

//    @Query("SELECT id,content,thread_creator FROM thread ua ;")
//    List<Thread> findAllThreads();
}
