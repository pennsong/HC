package com.qtc.hospitalcore.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/noLogin")
@Api(tags="匿名操作")
public class NoLoginController {

    // query
    @ApiOperation(value = "获取图片验证码")
    @GetMapping("/huoQuTuPianYanZhengMa")
    public PPResult<String> huoQuTuPianYanZhengMa() {
        // TODO: PP

        return null;
    }
    // query end

    // command
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public PPResult<DTO_login_R> login(@Valid @RequestBody DTO_login dto) {
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
