package com.qtc.hospitalcore.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PPUtil {

    public static UUID adminZhangHaoId = UUID.fromString("11111111-1111-1111-1111-111111111111");
    public static UUID yongHuZhangHaoId = UUID.fromString("11111111-1111-1111-1111-111111111112");
    public static UUID yiHuRenYuanZhangHaoId = UUID.fromString("11111111-1111-1111-1111-111111111113");

    public static Map stringToMap(String input) {
        if (StringUtils.isEmpty(input)) {
            input = "dummy: dummy";
        }

        Map map = new HashMap<String, Object>();
        String[] itemArray = input.split(",");

        for (String item : itemArray) {
            String[] itemPair = item.trim().split(":");
            map.put(itemPair[0].trim(), itemPair[1].trim());
        }

        return map;
    }

    public static String objectToJsonString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
