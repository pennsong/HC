package com.qtc.hospitalcore.domain.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PPUtil {
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
}
