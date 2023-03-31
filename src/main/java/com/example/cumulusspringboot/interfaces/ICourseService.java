package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course createCourse(Course course);
    Course updateCourse(Course course);
     boolean deleteCourse(Long id);





}
