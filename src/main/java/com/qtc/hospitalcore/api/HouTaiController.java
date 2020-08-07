package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.YiHuRenYuan;
import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.query.WenZhenView;
import com.qtc.hospitalcore.domain.query.YiHuRenYuanView;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/houTai")
public class HouTaiController {

    // query
    /**
     * 商品列表
     */
    @GetMapping("/changPin")
    public PPResult<List<ChangPinView>> ChangPin(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    /**
     * 医护人员列表
     */
    @GetMapping("/yiHuRenYuan")
    public PPResult<List<YiHuRenYuanView>> yiHuRenYuan(@RequestParam(defaultValue="") String queryKey, YiHuRenYuan.QuanXian quanXian, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @GetMapping("/wenZhen")
    public PPResult<List<WenZhenView>> wenZhen(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @GetMapping("/wenZhen/{id}")
    public PPResult<WenZhenView> wenZhen() {
        // TODO: PP

        return null;
    }

    // query end
}
