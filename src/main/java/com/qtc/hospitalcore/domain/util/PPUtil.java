package com.qtc.hospitalcore.domain.util;

import java.util.HashMap;
import java.util.Map;

public class PPUtil {
    public static Map stringToMap(String input) {
        Map map = new HashMap<String, Object>();
        String[] itemArray = input.split(",");

        for (String item : itemArray) {
            String[] itemPair = item.trim().split(":");
            map.put(itemPair[0].trim(), itemPair[1].trim());
        }

        return map;
    }
}
