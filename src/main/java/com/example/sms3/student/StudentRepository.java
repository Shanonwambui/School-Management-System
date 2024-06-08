package com.example.sms3.student;

import com.example.sms3.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentName(String studentName);
}
