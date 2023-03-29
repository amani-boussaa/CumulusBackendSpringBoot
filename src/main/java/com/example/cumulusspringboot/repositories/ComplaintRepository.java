package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(User user);
}
