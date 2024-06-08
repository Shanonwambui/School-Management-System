package com.example.sms3.institution;

import com.example.sms3.course.Course;
import com.example.sms3.student.Student;
import com.example.sms3.student.Views;
import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "institution")
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = InstitutionSerializer.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "institutionId")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    @Column(name = "institution_id")
    private Long institutionId;

    @JsonView(Views.StudentView.class)
    @Column(name = "institution_name")
    private String institutionName;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "institution_course",
            joinColumns = @JoinColumn(name = "institution_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )


    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "institution")
    private List<Student> student = new ArrayList<>();

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution institution = (Institution) o;
        return institutionId.equals(institution.institutionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionId);
    }



}
