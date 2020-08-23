package com.qtc.hospitalcore.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Slf4j
public class PPJsonTypeConverter implements AttributeConverter<PPJsonType, String> {

    @Override
    public String convertToDatabaseColumn(PPJsonType customerInfo) {
        if (customerInfo == null) {
            return null;
        }

        return customerInfo.getJsonString();
    }

    @Override
    public PPJsonType convertToEntityAttribute(String customerInfoJSON) {
        return new PPJsonType(customerInfoJSON);
    }
}