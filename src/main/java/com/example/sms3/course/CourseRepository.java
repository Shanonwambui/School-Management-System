package com.example.sms3.course;
import com.example.sms3.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameContaining(String searchTerm);
}