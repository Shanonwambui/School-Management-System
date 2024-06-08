package com.example.sms3.student;

import com.example.sms3.course.Course;
import com.example.sms3.course.CourseRepository;
import com.example.sms3.exception.ConflictException;
import com.example.sms3.exception.ResourceNotFoundException;
import com.example.sms3.institution.Institution;
import com.example.sms3.institution.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final InstitutionRepository institutionRepository;
    private final CourseRepository courseRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    public StudentService(StudentRepository studentRepository, InstitutionRepository institutionRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.institutionRepository = institutionRepository;
        this.courseRepository = courseRepository;

    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();}

    @Transactional
    public void saveStudent() {
        Institution institution1 = institutionRepository.findById(1L).orElse(null);
        Institution institution2 = institutionRepository.findById(2L).orElse(null);
        Institution institution3 = institutionRepository.findById(3L).orElse(null);
        Institution institution4 = institutionRepository.findById(4L).orElse(null);

        // Fetch or create courses
        Course course1 = courseRepository.findById(1L).orElse(null);
        Course course2 = courseRepository.findById(2L).orElse(null);
        Course course3 = courseRepository.findById(3L).orElse(null);
        Course course5 = courseRepository.findById(5L).orElse(null);


        if (institution1 != null && institution2 != null && institution3 != null && institution4 != null && course1 != null && course2 != null && course3 != null && course5 != null) {

            //Create and save the first student
            Student student1 = new Student();
            student1.setStudentName("Allan Doe");
            student1.setInstitution(institution1);
            student1.getCourses().add(course1);
            course1.getStudent().add(student1);
            studentRepository.save(student1);
            courseRepository.save(course1);

            //Create and save the second student
            Student student2 = new Student();
            student2.setStudentName("Jane Doe");
            student2.setInstitution(institution2);
            student2.getCourses().add(course2);
            course2.getStudent().add(student2);
            studentRepository.save(student2);
            courseRepository.save(course2);

            //Create and save the third student
            Student student3 = new Student();
            student3.setStudentName("John Doe");
            student3.setInstitution(institution3);
            student3.getCourses().add(course2);
            course2.getStudent().add(student3);
            studentRepository.save(student3);
            courseRepository.save(course2);

            //Create and save the fourth student
            Student student4 = new Student();
            student4.setStudentName("Mary Doe");
            student4.setInstitution(institution4);
            student4.getCourses().add(course3);
            course3.getStudent().add(student4);
            studentRepository.save(student4);
            courseRepository.save(course3);


            //Create and save the fifth student
            Student student5 = new Student();
            student5.setStudentName("Peter Doe");
            student5.setInstitution(institution1);
            student5.getCourses().add(course1);
            student5.getCourses().add(course5);
            course5.getStudent().add(student5);
            course1.getStudent().add(student5);
            studentRepository.save(student5);
            courseRepository.save(course1);
            courseRepository.save(course5);

            //Create and save the sixth student
            Student student6 = new Student();
            student6.setStudentName("Paul Doe");
            student6.setInstitution(institution2);
            student6.getCourses().add(course2);
            student6.getCourses().add(course1);
            course2.getStudent().add(student6);
            course1.getStudent().add(student6);
            studentRepository.save(student6);
            courseRepository.save(course2);
            courseRepository.save(course1);

            //Create and save the seventh student
            Student student7 = new Student();
            student7.setStudentName("James Doe");
            student7.setInstitution(institution3);
            student7.getCourses().add(course2);
            student7.getCourses().add(course3);
            course2.getStudent().add(student7);
            course3.getStudent().add(student7);
            studentRepository.save(student7);
            courseRepository.save(course2);
            courseRepository.save(course3);

            //Create and save the eighth student
            Student student8 = new Student();
            student8.setStudentName("Grace Doe");
            student8.setInstitution(institution4);
            student8.getCourses().add(course3);
            course3.getStudent().add(student8);
            studentRepository.save(student8);
            courseRepository.save(course3);

            //Create and save the ninth student
            Student student9 = new Student();
            student9.setStudentName("Joseph Doe");
            student9.setInstitution(institution1);
            student9.getCourses().add(course1);
            course1.getStudent().add(student9);
            studentRepository.save(student9);
            courseRepository.save(course1);

            //Create and save the tenth student
            Student student10 = new Student();
            student10.setStudentName("Mercy Doe");
            student10.setInstitution(institution2);
            student10.getCourses().add(course2);
            course2.getStudent().add(student10);
            studentRepository.save(student10);
            courseRepository.save(course2);
        }
    }

    public void addStudentToCourse(Long courseId, Long institutionId,Student newStudent) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));

        for (Student student : course.getStudent()) {
            if (student.getStudentId().equals(newStudent.getStudentId())) {
                throw new ConflictException("A student with the same ID already exists in this course.");
            }
        }

        newStudent.getCourses().add(course);
        newStudent.setInstitution(institution);
        course.getStudent().add(newStudent);
        studentRepository.save(newStudent);
        courseRepository.save(course);
    }
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Remove the student from all courses they're associated with
        for (Course course : student.getCourses()) {
            course.getStudent().remove(student);
            courseRepository.save(course);  // Save the course to update the changes in the database
        }

        // Now you can delete the student
        studentRepository.delete(student);
    }

    public Student editStudentName(Long id, String newName) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        student.setStudentName(newName);
        return studentRepository.save(student);
    }

    public void changeStudentCourse(Long studentId, Long oldCourseId, Long newCourseId) {
        logger.debug("Fetching student with ID: {}", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        logger.debug("Fetching old course with ID: {}", oldCourseId);
        Course oldCourse = courseRepository.findById(oldCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Old course not found"));

        logger.debug("Fetching new course with ID: {}", newCourseId);
        Course newCourse = courseRepository.findById(newCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("New course not found"));

        logger.debug("Checking if student is enrolled in the old course");
        if (!student.getCourses().contains(oldCourse)) {
            logger.error("The student is not enrolled in the old course.");
            throw new ConflictException("The student is not enrolled in the old course.");
        }

        logger.debug("Checking if the new course belongs to the same institution as the old course");
        boolean institutionMatch = oldCourse.getInstitution().stream()
                .anyMatch(oldInstitution -> newCourse.getInstitution().contains(oldInstitution));
        if (!institutionMatch) {
            logger.error("The new course does not belong to the same institution as the old course.");
            throw new ConflictException("The new course does not belong to the same institution as the old course.");
        }

        logger.debug("Removing student from old course and adding to new course");
        student.getCourses().remove(oldCourse);
        student.getCourses().add(newCourse);

        logger.debug("Updating courses' student lists");
        oldCourse.getStudent().remove(student);
        newCourse.getStudent().add(student);

        logger.debug("Saving updated student and courses");
        studentRepository.save(student);
        courseRepository.save(oldCourse);
        courseRepository.save(newCourse);
    }

    public void transferStudent(Long studentId, Long newInstitutionId, Long newCourseId) {
        logger.debug("Fetching student with ID: {}", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        logger.debug("Fetching new institution with ID: {}", newInstitutionId);
        Institution newInstitution = institutionRepository.findById(newInstitutionId)
                .orElseThrow(() -> new ResourceNotFoundException("New institution not found"));

        logger.debug("Fetching new course with ID: {}", newCourseId);
        Course newCourse = courseRepository.findById(newCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("New course not found"));

        logger.debug("Removing student from old institution and course");
        Institution oldInstitution = student.getInstitution();
        oldInstitution.getStudent().remove(student);
        student.getCourses().forEach(course -> course.getStudent().remove(student));

        logger.debug("Adding student to new institution and course");
        student.setInstitution(newInstitution);
        student.getCourses().clear();
        student.getCourses().add(newCourse);
        newInstitution.getStudent().add(student);
        newCourse.getStudent().add(student);

        logger.debug("Saving updated student, institution, and course");
        studentRepository.save(student);
        institutionRepository.save(oldInstitution);
        institutionRepository.save(newInstitution);
        courseRepository.save(newCourse);
    }
}