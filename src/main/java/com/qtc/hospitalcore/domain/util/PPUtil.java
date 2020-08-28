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

    public static UUID getZhangHaoId(int i) {
        return UUID.fromString("11111111-1111-1111-1111-" + String.format("%012d", i));
    }

    public static UUID getYongHuId(int i) {
        return UUID.fromString("22222222-2222-2222-2222-" + String.format("%012d", i));
    }

    public static UUID getYiHuRenYuanId(int i) {
        return UUID.fromString("33333333-3333-3333-3333-" + String.format("%012d", i));
    }


    public static UUID getChanPinId(int i) {
        return UUID.fromString("44444444-4444-4444-4444-" + String.format("%012d", i));
    }

    public static UUID getYaoPinId(int i) {
        return UUID.fromString("55555555-5555-5555-5555-" + String.format("%012d", i));
    }

    public static UUID getJianKangDangAnId(int i) {
        return UUID.fromString("66666666-6666-6666-6666-" + String.format("%012d", i));
    }

    public static UUID getWenZhenId(int i) {
        return UUID.fromString("77777777-7777-7777-7777-" + String.format("%012d", i));
    }

    public static UUID getPaiBanId(int i) {
        return UUID.fromString("88888888-8888-8888-8888-" + String.format("%012d", i));
    }

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
