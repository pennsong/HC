package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.WenZhen;
import com.qtc.hospitalcore.domain.YiHuRenYuan;
import com.qtc.hospitalcore.domain.YongHu;
import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.query.WenZhenView;
import com.qtc.hospitalcore.domain.query.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.query.YongHuView;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    // query
    @GetMapping("/yongHu")
    public PPResult<List<YongHuView>> yongHu(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @GetMapping("/yongHu/{id}")
    public PPResult<YongHuView> yongHu() {
        // TODO: PP

        return null;
    }

    @GetMapping("/yiHuRenYuan")
    public PPResult<List<YiHuRenYuanView>> yiHuRenYuan(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @GetMapping("/yiHuRenYuan/{id}")
    public PPResult<YiHuRenYuanView> yiHuRenYuan() {
        // TODO: PP

        return null;
    }

    /**
     * 获取就诊人列表
     *
     * <p>和健康档案列表相关</p>
     */
    @GetMapping("/huoQuJiuZhenRenLB")
    public PPResult<DTO_huoQuJiuZhenRenLB_R> huoQuJiuZhenRenLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJiuZhenRenLB_R {
        UUID jianKangDangAnId;
        UUID guanLianYongHuId;
    }

    /**
     * 获取就诊人信息
     *
     * <p>和健康档案相关</p>
     */
    @GetMapping("/huoQuJiuZhenRenXinXi")
    public PPResult<DTO_huoQuJiuZhenRenXinXi_R> huoQuJiuZhenRenLB(DTO_huoQuJiuZhenRenXinXi dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJiuZhenRenXinXi {
        UUID jianKangDangAnId;
    }

    @Data
    static class DTO_huoQuJiuZhenRenXinXi_R {
        UUID jianKangDangAnId;
        Map<String, Object> jiuZhenRenXinXiNeiRong;
    }

    /**
     * 获取药品信息
     */
    @GetMapping("/huoQuYaoPinXinXi")
    public PPResult<DTO_huoQuYaoPinXinXi_R> huoQuJiuZhenRenLB() {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuYaoPinXinXi_R {
        String yaoPinXinXiJson;
    }

    @GetMapping("/huoQuJiuZhenRenWenZhenLB")
    public PPResult<DTO_huoQuJiuZhenRenWenZhenLB_R> huoQuJiuZhenRenWenZhenLB(DTO_huoQuJiuZhenRenWenZhenLB dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJiuZhenRenWenZhenLB{
        UUID jianKangDangAnId;
    }

    @Data
    static class DTO_huoQuJiuZhenRenWenZhenLB_R {
        UUID jianKangDangAnId;
        UUID guanLianYongHuId;
    }
    // query end

    // command
    @PostMapping("/changPin")
    public PPResult<ChangPinView> changPin(@Valid @RequestBody DTO_changPin dto) {
        // TODO: PP

        return null;
    }

    static class DTO_changPin {
        UUID changPinId;
    }

    @PostMapping("/bianJichangPin")
    public PPResult<ChangPinView> changPin(@Valid @RequestBody DTO_bianJichangPin dto) {
        // TODO: PP

        return null;
    }

    static class DTO_bianJichangPin {
        UUID changPinId;
    }

    @PostMapping("/shanChuChangPin")
    public PPResult changPin(@Valid @RequestBody shanChuChangPin dto) {
        // TODO: PP

        return null;
    }

    static class shanChuChangPin {
        UUID changPinId;
    }

    @PostMapping("/shanChuYongHu")
    public PPResult shanChuYongHu(@Valid @RequestBody DTO_shanChuYongHu dto) {
        // TODO: PP

        return null;
    }

    static class DTO_shanChuYongHu {
        UUID yongHuId;
    }

    @PostMapping("/yiHuRenYuan")
    public PPResult yiHuRenYuan(@Valid @RequestBody DTO_yiHuRenYuan dto) {
        // TODO: PP

        return null;
    }

    static class DTO_yiHuRenYuan {
        String dengLuMing;
        String dengLuMiMa;
        String xingMing;
        String shenFenZhengHao;
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @PostMapping("/shanChuYiHuRenYuan")
    public PPResult shanChuYiHuRenYuan(@Valid @RequestBody DTO_shanChuYiHuRenYuan dto) {
        // TODO: PP

        return null;
    }

    static class DTO_shanChuYiHuRenYuan {
        UUID yiHuRenYuanId;
    }

    @PostMapping("/bianJiYiHuRenYuan")
    public PPResult bianJiYiHuRenYuan(@Valid @RequestBody DTO_bianJiYiHuRenYuan dto) {
        // TODO: PP

        return null;
    }

    static class DTO_bianJiYiHuRenYuan {
        UUID yiHuRenYuanId;
        String dengLuMiMa;
        String xingMing;
        String shenFenZhengHao;
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @PostMapping("/sheZhiYiHuRenYuanQuanXian")
    public PPResult sheZhiYiHuRenYuanQuanXian(@Valid @RequestBody DTO_sheZhiYiHuRenYuanQuanXian dto) {
        // TODO: PP

        return null;
    }

    static class DTO_sheZhiYiHuRenYuanQuanXian {
        UUID yiHuRenYuanId;
        Set<YiHuRenYuan.QuanXian> quanXianZu;
    }

    @PostMapping("/jieShuWenZhen")
    public PPResult jieShuWenZhen(@Valid @RequestBody DTO_jieShuWenZhen dto) {
        // TODO: PP

        return null;
    }

    static class DTO_jieShuWenZhen {
        UUID wenZhenId;
        WenZhen.JieGuo jieGuo;
        String beiZhu;
    }

    @PostMapping("/fenPeiWenZhen")
    public PPResult fenPeiWenZhen(@Valid @RequestBody DTO_fenPeiWenZhen dto) {
        // TODO: PP

        return null;
    }

    static class DTO_fenPeiWenZhen {
        UUID wenZhenId;
        UUID wenZhenYiSheng;
        UUID bianJiBingLiYiSheng;
        UUID kaiJuChuFangYiSheng;
        UUID queRenChuFangYiSheng;
    }

    @PostMapping("/zhuanZhen")
    public PPResult zhuanZhen(@Valid @RequestBody DTO_zhuanZhen dto) {
        // TODO: PP

        return null;
    }

    static class DTO_zhuanZhen {
        UUID wenZhenId;
        UUID wenZhenYiSheng;
        UUID bianJiBingLiYiSheng;
        UUID kaiJuChuFangYiSheng;
        UUID queRenChuFangYiSheng;
    }

    @PostMapping("/anPaiHuiZhen")
    public PPResult anPaiHuiZhen(@Valid @RequestBody DTO_anPaiHuiZhen dto) {
        // TODO: PP

        return null;
    }

    static class DTO_anPaiHuiZhen {
        UUID wenZhenId;
        Map<String, Object> huiZhenXinXiNeiRong;
    }
    // command end
}
