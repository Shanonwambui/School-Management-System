package com.example.sms3.course;

import com.example.sms3.institution.Institution;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CourseSerializer extends StdSerializer<Course> {
    public CourseSerializer() {
        this(null);
    }

    public CourseSerializer(Class<Course> t) {
        super(t);
    }

    @Override
    public void serialize(
            Course course,
            JsonGenerator generator,
            SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("courseId", course.getCourseId());
        generator.writeStringField("courseName", course.getCourseName());
        // Start institutions array
        generator.writeArrayFieldStart("institutions");
        for (Institution institution : course.getInstitution()) {
            generator.writeStartObject();
            generator.writeNumberField("institutionId", institution.getInstitutionId());
            generator.writeStringField("institutionName", institution.getInstitutionName());
            generator.writeEndObject();
        }
        // End institutions array
        generator.writeEndArray();
        // End institutions array
        generator.writeEndObject();
    }

}
