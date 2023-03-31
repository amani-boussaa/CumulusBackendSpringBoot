package com.example.cumulusspringboot.services;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.Course;
import com.example.cumulusspringboot.interfaces.ICourseService;
import com.example.cumulusspringboot.repositories.CourseRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
@Service
@AllArgsConstructor
public class CourseService implements ICourseService {
    CourseRepo courseRepo;
    // *****************************************************************************************************
    @Override
    public List<Course> getAllCourses() {
            return courseRepo.findAll();
        }
    // *****************************************************************************************************
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No course found with id " + id));
    }
    // *****************************************************************************************************
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }
    // *****************************************************************************************************
    @Override
    public Course updateCourse(Course course) {
        return courseRepo.save(course);
    }
    // *****************************************************************************************************
    public boolean deleteCourse(Long id) {
        if (!courseRepo.existsById(id)) {
            return false;
        }
        courseRepo.deleteById(id);
        return true;
    }
    public void uploadFile (MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\Users\\Malik\\IdeaProjects\\CumulusBackendSpringBoot\\cccc\\"+file.getOriginalFilename()));
    }

    }



