package com.example.sms3.student;
import com.example.sms3.course.Course;
import com.example.sms3.institution.Institution;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;


public class StudentSerializer extends StdSerializer<Student> {

    public StudentSerializer() {
        this(null);
    }

    public StudentSerializer(Class<Student> t) {
        super(t);
    }

    @Override
    public void serialize(
            Student student,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("studentId", student.getStudentId());
        jsonGenerator.writeStringField("studentName", student.getStudentName());

        jsonGenerator.writeFieldName("institution");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("institutionId", student.getInstitution().getInstitutionId());
        jsonGenerator.writeStringField("institutionName", student.getInstitution().getInstitutionName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeArrayFieldStart("courses");
        for (Course course : student.getCourses()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("courseId", course.getCourseId());
            jsonGenerator.writeStringField("courseName", course.getCourseName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}