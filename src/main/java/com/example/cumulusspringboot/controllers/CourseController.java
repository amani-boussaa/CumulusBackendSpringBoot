package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Course;
import com.example.cumulusspringboot.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    // *****************************************************************************************************
    private final CourseService courseService;

    // *****************************************************************************************************
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // *****************************************************************************************************
    @PostMapping("/courses")
    public ResponseEntity<Course> getCourseById(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    // *****************************************************************************************************
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    // *****************************************************************************************************
    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course existingCourse = courseService.getCourseById(id);
        if (existingCourse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setInstructor(course.getInstructor());
        existingCourse.setPrice(course.getPrice());
        Course updatedCourse = courseService.updateCourse(existingCourse);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    // *****************************************************************************************************
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        courseService.uploadFile(file);

    }

}
