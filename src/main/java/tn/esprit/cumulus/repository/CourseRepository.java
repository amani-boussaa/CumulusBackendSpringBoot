package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.cumulus.entity.Course;


public interface CourseRepository  extends JpaRepository<Course, Long> {
}
