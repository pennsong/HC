package com.qtc.hospitalcore.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtc.hospitalcore.domain.wenzhen.WenZhen;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Slf4j
public class YuFuKuanConverter implements AttributeConverter<WenZhen.YuFuKuan, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(WenZhen.YuFuKuan customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public WenZhen.YuFuKuan convertToEntityAttribute(String customerInfoJSON) {

        WenZhen.YuFuKuan customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON, WenZhen.YuFuKuan.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return customerInfo;
    }

}