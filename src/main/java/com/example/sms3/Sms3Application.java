package com.example.sms3;

import com.example.sms3.course.CourseService;
import com.example.sms3.institution.Institution;
import com.example.sms3.institution.InstitutionRepository;
import com.example.sms3.institution.InstitutionService;
import com.example.sms3.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sms3Application implements CommandLineRunner {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(Sms3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {




    }


}
