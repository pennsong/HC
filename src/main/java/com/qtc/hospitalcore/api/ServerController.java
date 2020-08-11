package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.yonghu.ChuangJianYongHuCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/server")
@Api(tags="系统调用操作")
public class ServerController {
    @Autowired
    PPCommandGateway ppCommandGateway;

    // command

    @ApiOperation(value = "创建用户")
    @PostMapping("/chuangJianYongHu")
    public PPResult chuangJianYongHu(@Valid @RequestBody DTO_chuangJianYongHu dto) {
        ppCommandGateway.sendAndWait(
                new ChuangJianYongHuCmd(
                        dto.getYongHuId(),
                        dto.getShouJiHaoMa()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_chuangJianYongHu {
        UUID yongHuId;
        String shouJiHaoMa;
    }
    // command end
}
