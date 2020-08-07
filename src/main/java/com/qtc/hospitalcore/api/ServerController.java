package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.WenZhen;
import com.qtc.hospitalcore.domain.YiHuRenYuan;
import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.query.WenZhenView;
import com.qtc.hospitalcore.domain.query.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.ChuangJianYongHuCmd;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired
    CommandGateway commandGateway;

    // command
    /**
     * 创建用户
     */
    @PostMapping("/chuangJianYongHu")
    public PPResult chuangJianYongHu(@Valid @RequestBody DTO_chuangJianYongHu dto) {
        commandGateway.sendAndWait(new ChuangJianYongHuCmd(
                dto.getYongHuId(),
                dto.getShouJiHaoMa()
              )
        );

        return PPResult.getPPOK();
    }

    @Value
    static class DTO_chuangJianYongHu {
        UUID yongHuId;
        String shouJiHaoMa;
    }
    // command end
}
