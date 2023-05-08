package com.example.cumulusspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cumulusspringboot.entities.Course;


public interface CourseRepository  extends JpaRepository<Course, Long> {
}
