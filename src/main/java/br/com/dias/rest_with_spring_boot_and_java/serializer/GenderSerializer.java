package br.com.dias.rest_with_spring_boot_and_java.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String gender, JsonGenerator gen,
                          SerializerProvider serializerProvider) throws IOException {

        String formatedGender = "Male".equals(gender) ? "M" : "F";
        gen.writeString(formatedGender);
    }
}
