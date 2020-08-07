package com.qtc.hospitalcore;

import com.qtc.hospitalcore.domain.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    private CommandGateway commandGateway;

    private static final String SESSION_KEY = "PPKEY";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("code1")
    public String code(@RequestParam String val,  HttpServletRequest request, HttpServletResponse response) {
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, val);

        return "ok";
    }

    @GetMapping("code2")
    public String code(HttpServletRequest request, HttpServletResponse response) {
        String result = sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY).toString();

        return result;
    }

    @PostMapping("test")
    public String test(@RequestBody TestDTO testDTO) {
        commandGateway.sendAndWait(new YiHuRenYuan_ChuangJianCmd(
                UUID.randomUUID(),
                testDTO.quanXianSet,
                testDTO.xinXiMap
        ));

        return "ok";
    }

    @Data
    static class TestDTO {
        Set<YiHuRenYuan.QuanXian> quanXianSet;

        Map<String, Object> xinXiMap;
    }
}
