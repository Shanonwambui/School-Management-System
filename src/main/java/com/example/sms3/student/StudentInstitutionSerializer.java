package com.example.sms3.student;

import com.example.sms3.institution.Institution;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class StudentInstitutionSerializer extends StdSerializer<Institution> {

    public StudentInstitutionSerializer() {
        this(null);
    }

    public StudentInstitutionSerializer(Class<Institution> t) {
        super(t);
    }

    @Override
    public void serialize(Institution institution, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("institutionId", institution.getInstitutionId());
        gen.writeStringField("institutionName", institution.getInstitutionName());
        gen.writeEndObject();
    }
}
