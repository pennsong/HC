package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.wenzhen.WenZhen;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags="Admin操作")
public class AdminController {
    // query
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/huoQuYongHuLB")
    public PPResult<List<YongHuView>> huoQuYongHuLB(@RequestParam(defaultValue="") String queryKey, @PageableDefault(page=0, size=20) Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/huoQuYongHu/{yongHuId}")
    public PPResult<YongHuView> huoQuYongHu(@PathVariable UUID yongHuId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取医护人员列表")
    @GetMapping("/huoQuYiHuRenYuanLB")
    public PPResult<List<YiHuRenYuanView>> huoQuYiHuRenYuanLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取医护人员")
    @GetMapping("/huoQuYiHuRenYuan/{yiHuRenYuanId}")
    public PPResult<YiHuRenYuanView> yiHuRenYuan(@PathVariable UUID yiHuRenYuanId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取问诊列表")
    @GetMapping("/huoQuWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuWenZhenLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取药品信息")
    @GetMapping("/huoQuYaoPinXinXi")
    public PPResult<DTO_huoQuYaoPinXinXi_R> huoQuJiuZhenRenLB() {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuYaoPinXinXi_R {
        String yaoPinXinXiJson;
    }

    @ApiOperation(value = "获取健康档案列表")
    @GetMapping("/huoQuJianKangDangAnLB")
    public PPResult<List<JianKangDangAnView>> huoQuJianKangDangAnLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取健康档案问诊列表")
    @GetMapping("/huoQuJianKangDangAnWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuJianKangDangAnWenZhenLB(DTO_huoQuJiuZhenRenWenZhenLB dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJiuZhenRenWenZhenLB{
        UUID jianKangDangAnId;
    }

    // query end

    // command
    @ApiOperation(value = "创建产品")
    @PostMapping("/chuangJianChangPin")
    public PPResult<UUID> chuangJianChangPin(@Valid @RequestBody DTO_chuangJianChangPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_chuangJianChangPin {
        String fuWuDaFenLei;
        String fuWuLeiXing;
        String chanPingMing;
        BigDecimal yuFuFei;
        BigDecimal shiChangJia;
    }

    @ApiOperation(value = "编辑产品")
    @PostMapping("/bianJichangPin")
    public PPResult bianJichangPin(@Valid @RequestBody DTO_bianJichangPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJichangPin {
        UUID changPinId;
        String fuWuDaFenLei;
        String fuWuLeiXing;
        String chanPingMing;
        BigDecimal yuFuFei;
        BigDecimal shiChangJia;
    }

    @ApiOperation(value = "删除产品")
    @PostMapping("/shanChuChangPin")
    public PPResult changPin(@Valid @RequestBody shanChuChangPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class shanChuChangPin {
        UUID changPinId;
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/shanChuYongHu")
    public PPResult shanChuYongHu(@Valid @RequestBody DTO_shanChuYongHu dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_shanChuYongHu {
        UUID yongHuId;
    }

    @ApiOperation(value = "创建医护人员")
    @PostMapping("/chuangJianYiHuRenYuan")
    public PPResult<UUID> chuangJianYiHuRenYuan(@Valid @RequestBody DTO_chuangJianYiHuRenYuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_chuangJianYiHuRenYuan {
        String dengLuMing;
        String dengLuMiMa;
        String xingMing;
        String shenFenZhengHao;
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @ApiOperation(value = "删除医护人员")
    @PostMapping("/shanChuYiHuRenYuan")
    public PPResult shanChuYiHuRenYuan(@Valid @RequestBody DTO_shanChuYiHuRenYuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_shanChuYiHuRenYuan {
        UUID yiHuRenYuanId;
    }

    @ApiOperation(value = "编辑医护人员")
    @PostMapping("/bianJiYiHuRenYuan")
    public PPResult bianJiYiHuRenYuan(@Valid @RequestBody DTO_bianJiYiHuRenYuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiYiHuRenYuan {
        UUID yiHuRenYuanId;
        String dengLuMiMa;
        String xingMing;
        String shenFenZhengHao;
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @ApiOperation(value = "设置医护人员权限")
    @PostMapping("/sheZhiYiHuRenYuanQuanXian")
    public PPResult sheZhiYiHuRenYuanQuanXian(@Valid @RequestBody DTO_sheZhiYiHuRenYuanQuanXian dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_sheZhiYiHuRenYuanQuanXian {
        UUID yiHuRenYuanId;
        Set<YiHuRenYuan.QuanXian> quanXianZu;
    }

    @ApiOperation(value = "结束问诊")
    @PostMapping("/jieShuWenZhen")
    public PPResult jieShuWenZhen(@Valid @RequestBody DTO_jieShuWenZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_jieShuWenZhen {
        UUID wenZhenId;
        WenZhen.JieGuo jieGuo;
        String beiZhu;
    }

    @ApiOperation(value = "分配问诊")
    @PostMapping("/fenPeiWenZhen")
    public PPResult fenPeiWenZhen(@Valid @RequestBody DTO_fenPeiWenZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_fenPeiWenZhen {
        UUID wenZhenId;
        UUID wenZhenYiSheng;
        UUID bianJiBingLiYiSheng;
        UUID kaiJuChuFangYiSheng;
        UUID queRenChuFangYiSheng;
    }

    @ApiOperation(value = "转诊")
    @PostMapping("/zhuanZhen")
    public PPResult zhuanZhen(@Valid @RequestBody DTO_zhuanZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_zhuanZhen {
        UUID wenZhenId;
        UUID wenZhenYiSheng;
        UUID bianJiBingLiYiSheng;
        UUID kaiJuChuFangYiSheng;
        UUID queRenChuFangYiSheng;
    }

    @ApiOperation(value = "安排会诊")
    @PostMapping("/anPaiHuiZhen")
    public PPResult anPaiHuiZhen(@Valid @RequestBody DTO_anPaiHuiZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_anPaiHuiZhen {
        UUID wenZhenId;
        Map<String, Object> huiZhenXinXiNeiRong;
    }
    // command end
}
