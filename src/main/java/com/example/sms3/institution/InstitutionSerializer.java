package com.example.sms3.institution;

import com.example.sms3.course.Course;
import com.example.sms3.student.Student;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class InstitutionSerializer extends StdSerializer<Institution> {
    public InstitutionSerializer() {
        this(null);
    }

    public InstitutionSerializer(Class<Institution> t) {
        super(t);
    }

    @Override
    public void serialize(
            Institution institution,
            JsonGenerator generator,
            SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("institutionId", institution.getInstitutionId());
        generator.writeStringField("institutionName", institution.getInstitutionName());
        // Start courses array
        generator.writeArrayFieldStart("courses");
        for (Course course : institution.getCourses()) {
            generator.writeStartObject();
            generator.writeNumberField("courseId", course.getCourseId());
            generator.writeStringField("courseName", course.getCourseName());
            generator.writeEndObject();
        }
        // End courses array
        generator.writeEndArray();
        // Start students array
        generator.writeArrayFieldStart("students");
        for (Student student : institution.getStudent()) {
            generator.writeStartObject();
            generator.writeNumberField("studentId", student.getStudentId());
            generator.writeStringField("studentName", student.getStudentName());
            generator.writeEndObject();
        }
        // End students array
        generator.writeEndArray();
        generator.writeEndObject();
    }
}
