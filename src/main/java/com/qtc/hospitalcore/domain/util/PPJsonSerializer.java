package com.qtc.hospitalcore.domain.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class PPJsonSerializer extends StdSerializer<PPJsonType> {

    public PPJsonSerializer() {
        this(null);
    }

    public PPJsonSerializer(Class<PPJsonType> t) {
        super(t);
    }

    @Override
    public void serialize(
            PPJsonType value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        String tmp = value.getJsonString();
        if (tmp != null) {
            tmp = StringUtils.trimLeadingCharacter(tmp, '{');
            tmp = StringUtils.trimTrailingCharacter(tmp, '}');
        } else {
            tmp = "";
        }
        jgen.writeRaw(tmp);
        jgen.writeEndObject();
    }
}