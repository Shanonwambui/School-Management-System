package com.example.sms3.student;

import com.example.sms3.course.Course;
import com.example.sms3.exception.ConflictException;
import com.example.sms3.exception.ResourceNotFoundException;
import com.example.sms3.institution.Institution;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @JsonView(Views.StudentView.class)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addStudentToCourse/{courseId}/{institutionId}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long institutionId, @RequestBody Student newStudent) {
        try {
            studentService.addStudentToCourse(courseId, institutionId,newStudent);
            return ResponseEntity.ok().build();
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editStudentName(@PathVariable Long id, @RequestParam String newName) {
        Student updatedStudent = studentService.editStudentName(id, newName);
        return ResponseEntity.ok(updatedStudent);
    }

    @PutMapping("/{studentId}/changeCourse/{oldCourseId}/{newCourseId}")
    public ResponseEntity<?> changeStudentCourse(@PathVariable Long studentId, @PathVariable Long oldCourseId, @PathVariable Long newCourseId) {
        studentService.changeStudentCourse(studentId, oldCourseId, newCourseId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{studentId}/transferStudent/{newInstitutionId}/{newCourseId}")
    public ResponseEntity<Void> transferStudent(@PathVariable Long studentId, @PathVariable Long newInstitutionId, @PathVariable Long newCourseId) {
        studentService.transferStudent(studentId, newInstitutionId, newCourseId);
        return ResponseEntity.ok().build();
    }

}