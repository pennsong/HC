package com.qtc.hospitalcore.api;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class PPResult<T> {
    String code;
    String msg;
    T payload;

    public static PPResult<Object> getPPOK() {
        return new PPResult("1", "OK", null);
    }

    public static PPResult getPPResultOK(Object payload) {
        return new PPResult("1", null, payload);
    }
}
