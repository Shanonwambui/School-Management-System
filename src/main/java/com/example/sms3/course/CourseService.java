package com.example.sms3.course;

import com.example.sms3.exception.ConflictException;
import com.example.sms3.exception.ResourceNotFoundException;
import com.example.sms3.institution.Institution;
import com.example.sms3.institution.InstitutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstitutionRepository institutionRepository) {
        this.courseRepository = courseRepository;
        this.institutionRepository = institutionRepository;

    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public void saveCourse() {
        Institution institution1 = institutionRepository.findById(1L).orElse(null);
        Institution institution2 = institutionRepository.findById(2L).orElse(null);
        Institution institution3 = institutionRepository.findById(3L).orElse(null);
        Institution institution4 = institutionRepository.findById(4L).orElse(null);


        if(institution1 !=null && institution2 !=null && institution3 !=null && institution4 !=null ) {

            // Create and save the first course
            Course course1 = new Course();
            course1.setCourseName("Computer Science");
            course1.getInstitution().add(institution1);
            course1.getInstitution().add(institution2);
            courseRepository.save(course1);

            // Update institutions with the new course
            institution1.getCourses().add(course1);
            institution2.getCourses().add(course1);
            institutionRepository.saveAll(Arrays.asList(institution1, institution2));

            // Create and save the second course
            Course course2 = new Course();
            course2.setCourseName("Information Technology");
            course2.getInstitution().add(institution2);
            course2.getInstitution().add(institution3);
            courseRepository.save(course2);

            // Update institutions with the new course
            institution2.getCourses().add(course2);
            institution3.getCourses().add(course2);
            institutionRepository.saveAll(Arrays.asList(institution2, institution3));

            // Create and save the third course
            Course course3 = new Course();
            course3.setCourseName("Software Engineering");
            course3.getInstitution().add(institution3);
            course3.getInstitution().add(institution4);
            courseRepository.save(course3);

            // Update institutions with the new course
            institution3.getCourses().add(course3);
            institution4.getCourses().add(course3);
            institutionRepository.saveAll(Arrays.asList(institution3, institution4));

            // Create and save the fourth course
            Course course4 = new Course();
            course4.setCourseName("Computer Engineering");
            course4.getInstitution().add(institution4);
            course4.getInstitution().add(institution1);
            courseRepository.save(course4);

            // Update institutions with the new course
            institution4.getCourses().add(course4);
            institution1.getCourses().add(course4);
            institutionRepository.saveAll(Arrays.asList(institution4, institution1));
        }



    }
    public List <Course> sortCourseByName(String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, "courseName")
                : Sort.by(Sort.Direction.DESC, "courseName");
        return courseRepository.findAll(sort);
    }

    public List<Course> searchCourse(String searchTerm) {
        return courseRepository.findByCourseNameContaining(searchTerm);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Remove the course from all institutions that reference it
        for (Institution institution : course.getInstitution()) {
            institution.getCourses().remove(course);
            institutionRepository.save(institution);
        }

        // Now you can delete the course
        courseRepository.delete(course);
    }

    public void addCourseToInstitution(Long institutionId, Course newCourse) {
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));

        for (Course course : institution.getCourses()) {
            if (course.getCourseName().equals(newCourse.getCourseName())) {
                throw new ConflictException("A course with the same name already exists in this institution.");
            }
        }

        newCourse.getInstitution().add(institution);
        institution.getCourses().add(newCourse);
        courseRepository.save(newCourse);
        institutionRepository.save(institution);
    }

    public Course editCourseName(Long id, String newName) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        course.setCourseName(newName);
        return courseRepository.save(course);
    }





}