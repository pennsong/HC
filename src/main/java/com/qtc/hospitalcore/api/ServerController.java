package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ChuangJianCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        // 参数相关检查
        // 参数相关检查 end
        ppCommandGateway.sendAndWait(
                new YongHu_ChuangJianCmd(
                        dto.getYongHuId(),
                        dto.getShouJiHaoMa(),
                        dto.getWeiXinOpenId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_chuangJianYongHu {
        UUID yongHuId;
        String shouJiHaoMa;
        String weiXinOpenId;
    }
    // command end
}
