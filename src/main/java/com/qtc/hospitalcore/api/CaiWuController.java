package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.YiHuRenYuan;
import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.query.WenZhenView;
import com.qtc.hospitalcore.domain.query.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.util.BiZhong;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/caiWu")
public class CaiWuController {

    // query
    /**
     * 获取问诊付款信息列表
     */
    @GetMapping("/huoQuWenZhenFuKuanXinXiLB")
    public PPResult huoQuWenZhenFuKuanXinXiLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    /**
     * 获取问诊付款信息
     */
    @GetMapping("/huoQuWenZhenFuKuanXinXi/{wenZhenId}")
    public PPResult huoQuWenZhenFuKuanXinXi(@PathVariable UUID wenZhenId) {
        // TODO: PP

        return null;
    }
    // query end

    // command
    @PostMapping("/buChongWenZhenFuKuan")
    public PPResult buChongWenZhenFuKuan(@Valid @RequestBody DTO_buChongWenZhenFuKuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_buChongWenZhenFuKuan {
        UUID wenZhenId;
        Long buChongFuKuanRiQi;
        String fuKuanFang;
        BigDecimal fuKuanE;
        BiZhong biZhong;
        Float fuKuanDangRiHuiLv;
        BigDecimal renMinBiZiDongHuanSuan;
        String[] fuKuanPingZheng;
        Map<String, Object> fuKuanXinXi;
    }

    @PostMapping("/faQiWenZhenTuiKuan")
    public PPResult faQiTuiKuan(@Valid @RequestBody DTO_faQiWenZhenTuiKuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_faQiWenZhenTuiKuan {
        UUID wenZhenId;
        Long tuiKuanRiQi;
        String shouKuanZhangHuMing;
        String shouKuanZhangHu;
        BigDecimal tuiKuanE;
        String[] tuiKuanPingZheng;
        Map<String, Object> tuiKuanXinXi;
    }
    // command end
}
