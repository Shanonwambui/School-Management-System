package com.example.sms3.course;

import com.example.sms3.exception.ConflictException;
import com.example.sms3.institution.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/sortByName")
    public List<Course> sortCourseByName(@RequestParam String sortOrder){
        return courseService.sortCourseByName(sortOrder);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/search")
    public List<Course> searchCourse(@RequestParam String searchTerm){
        return courseService.searchCourse(searchTerm);
    }


    @PostMapping("/addCourseToInstitution/{institutionId}")
    public ResponseEntity<?> addCourseToInstitution(@PathVariable Long institutionId, @RequestBody Course newCourse) {
        try {
            courseService.addCourseToInstitution(institutionId, newCourse);
            return ResponseEntity.ok().build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editCourseName(@PathVariable Long id, @RequestParam String newName) {
        Course updatedCourse = courseService.editCourseName(id, newName);
        return ResponseEntity.ok(updatedCourse);
    }




}