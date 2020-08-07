package com.qtc.hospitalcore.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/noLogin")
public class NoLoginController {

    // query
    /**
     * 获取图片验证码
     */
    @GetMapping("/huoQuTuPianYanZhengMa")
    public PPResult huoQuTuPianYanZhengMa() {
        // TODO: PP

        return null;
    }
    // query end

    // command
    @PostMapping("/login")
    public PPResult<DTO_login_R> login() {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_login {
        String dengLuMing;
        String mimA;
        String tuXingYanZhengMa;
    }

    @Data
    static class DTO_login_R {
        List<String> jueSeZu;
    }
    //command end
}
