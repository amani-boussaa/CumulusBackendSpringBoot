package com.example.cumulusspringboot.repositories.oubaid;

import com.example.cumulusspringboot.entities.oubaid.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachementRepo extends JpaRepository<Attachment, Long> {

}
