package com.example.cumulusspringboot.repositories;
import java.util.Optional;
import com.example.cumulusspringboot.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
    Optional<Course> findById(Long id);
}
