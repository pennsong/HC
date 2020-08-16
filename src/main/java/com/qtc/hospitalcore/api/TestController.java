package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.exception.PPException;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_ShanChuCmd;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    CommandGateway commandGateway;

    private static final String SESSION_KEY = "PP_KEY";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/t1")
    public String test1(HttpServletRequest request, HttpServletResponse response) {
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, "abc");
        return "ok";
    }

    @GetMapping("/t2")
    public String test2(HttpServletRequest request, HttpServletResponse response) {
        String result = String.valueOf(sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY));

        return result;
    }

    @GetMapping("/t3")
    public PPResult test3() {
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ExtChuangJianYongHuCmd(
                                UUID.randomUUID(),
                                "s1",
                                "o1"
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPOK();
    }

    @GetMapping("/shanChuZhangHao/{id}")
    public PPResult shanChuZhangHao(@PathVariable UUID id) {
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ZhangHao_ShanChuCmd(
                               id
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPOK();
    }
}
