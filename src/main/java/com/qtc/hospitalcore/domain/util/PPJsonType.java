package com.qtc.hospitalcore.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.qtc.hospitalcore.domain.exception.PPSystemException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PPJsonType {

    public PPJsonType(String jsonString) {
        this.jsonString = jsonString;
    }

    public PPJsonType(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(newJavaTimeModule());
        objectMapper.setTimeZone(TimeZone.getTimeZone(UTC));
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            this.jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            new PPSystemException(e.getMessage());
        }
    }

    String jsonString;

    private JavaTimeModule newJavaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(OffsetDateTime.class, offsetDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        module.addDeserializer(OffsetDateTime.class, offsetDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return module;
    }

    private StdSerializer<OffsetDateTime> offsetDateTimeSerializer(DateTimeFormatter formatter) {
        return new OffsetDateTimeSerializer(OffsetDateTimeSerializer.INSTANCE, false, formatter) {
        };
    }

    private StdDeserializer<OffsetDateTime> offsetDateTimeDeserializer(DateTimeFormatter formatter) {
        return new InstantDeserializer<OffsetDateTime>(InstantDeserializer.OFFSET_DATE_TIME, formatter) {
        };
    }
}

