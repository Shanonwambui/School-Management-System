package com.example.sms3.student;
import com.example.sms3.course.Course;
import com.example.sms3.institution.Institution;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.example.sms3.student.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentId")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonView(Views.StudentView.class)
    @Column(name = "student_id")
    private Long studentId;

    @JsonView(Views.StudentView.class)
    @Column(name = "student_name")
    private String studentName;

    @JsonSerialize(using = StudentCourseSerializer.class)
    @JsonView(Views.StudentView.class)
    @ManyToMany(mappedBy = "student")
    private List<Course> courses = new ArrayList<>();


    @JsonSerialize(using = StudentInstitutionSerializer.class)
    @JsonView(Views.StudentView.class)
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;



}