package com.example.sms3.student;

import com.example.sms3.course.Course;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class StudentCourseSerializer extends StdSerializer<List<Course>> {
    public StudentCourseSerializer() {
        this(null);
    }
    public StudentCourseSerializer(Class<List<Course>>  t) {
        super(t);
    }
    @Override
    public void serialize(List<Course> courses, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (Course course : courses) {
            gen.writeStartObject();
            gen.writeNumberField("courseId", course.getCourseId());
            gen.writeStringField("courseName", course.getCourseName());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
