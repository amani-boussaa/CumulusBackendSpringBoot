package com.example.cumulusspringboot.repositories;
import java.util.Optional;
import com.example.cumulusspringboot.entities.Certif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertifRepo extends JpaRepository<Certif,Long> {
    Optional<Certif> findById(Long id);
}
