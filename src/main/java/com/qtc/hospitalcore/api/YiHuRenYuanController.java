package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.wenzhen.WenZhen;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/yiHuRenYuan")
@Api(tags="医护人员操作")
public class YiHuRenYuanController {
    // query
    @ApiOperation(value = "获取问诊")
    @GetMapping("/huoQuWenZhen/{wenZhenId}")
    public PPResult<WenZhenView> huoQuWenZhen(
            @PathVariable UUID wenZhenDanId
    ) {
        // TODO: PP
        // session取yongHuId

        return null;
    }

    // query end

    // command
    @ApiOperation(value = "创建问诊报告")
    @PostMapping("/chuangJianWenZhenBaoGao")
    public PPResult<WenZhen> chuangJianWenZhen(@Valid @RequestBody DTO_chuangJianWenZhenBaoGao dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_chuangJianWenZhenBaoGao {
        UUID wenZhenId;
        String baoGaoWenBen;
    }

    @ApiOperation(value = "编辑问诊健康档案")
    @PostMapping("/bianJiWenZhenJianKangDangAn")
    public PPResult<WenZhen> bianJiWenZhenJianKangDangAn(@Valid @RequestBody DTO_bianJiWenZhenJianKangDangAn dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenJianKangDangAn {
        UUID wenZhenId;
        Map<String, Object> jianKangDangAnNeiRong;
    }

    @ApiOperation(value = "编辑问诊主要诊断")
    @PostMapping("/bianJiWenZhenZhuYaoZhenDuan")
    public PPResult<WenZhen> bianJiWenZhenZhuYaoZhenDuan(@Valid @RequestBody DTO_bianJiWenZhenZhuYaoZhenDuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenZhuYaoZhenDuan {
        UUID wenZhenId;
        String zhuYaoZhenDuan;
    }

    @ApiOperation(value = "编辑问诊详细治疗经过")
    @PostMapping("/bianJiWenZhenXiangXiZhiLiaoJingGuo")
    public PPResult<WenZhen> bianJiWenZhenXiangXiZhiLiaoJingGuo(@Valid @RequestBody DTO_bianJiWenZhenXiangXiZhiLiaoJingGuo dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenXiangXiZhiLiaoJingGuo {
        UUID wenZhenId;
        Map<String, Object> xiangXiZhiLiaoJingGuoNeiRong;
    }

    @ApiOperation(value = "编辑问诊检查总结")
    @PostMapping("/bianJiWenZhenJianChaZongJie")
    public PPResult<WenZhen> bianJiWenZhenJianChaZongJie(@Valid @RequestBody DTO_bianJiWenZhenJianChaZongJie dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenJianChaZongJie {
        UUID wenZhenId;
        Map<String, Object> jianChaZongJieNeiRong;
    }

    @ApiOperation(value = "编辑问诊电子影像")
    @PostMapping("/bianJiWenZhenDianZiYingXiang")
    public PPResult<WenZhen> bianJiWenZhenDianZiYingXiang(@Valid @RequestBody DTO_bianJiWenZhenDianZiYingXiang dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenDianZiYingXiang {
        UUID wenZhenId;
        Map<String, Object> dianZiYingXiangNeiRong;
    }

    @ApiOperation(value = "编辑问诊其他材料")
    @PostMapping("/bianJiWenZhenQiTaCaiLiao")
    public PPResult<WenZhen> bianJiWenZhenQiTaCaiLiao(@Valid @RequestBody DTO_bianJiWenZhenQiTaCaiLiao dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenQiTaCaiLiao {
        UUID wenZhenId;
        Map<String, Object> qiTaCaiLiaoNeiRong;
    }

    @ApiOperation(value = "编辑问诊总结")
    @PostMapping("/bianJiWenZhenZongJie")
    public PPResult<WenZhen> bianJiWenZhenZongJie(@Valid @RequestBody DTO_bianJiWenZhenZongJie dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiWenZhenZongJie {
        UUID wenZhenId;
        Map<String, Object> zongJieNeiRong;
    }

    @ApiOperation(value = "开具问诊处方")
    @PostMapping("/kaiJuWenZhenChuFang")
    public PPResult<WenZhen> kaiJuWenZhenChuFang(@Valid @RequestBody DTO_kaiJuWenZhenChuFang dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_kaiJuWenZhenChuFang {
        UUID wenZhenId;
        Map<String, Object> chuFangNeiRong;
    }

    @ApiOperation(value = "确认问诊处方")
    @PostMapping("/queRenWenZhenChuFang")
    public PPResult<WenZhen> queRenWenZhenChuFang(@Valid @RequestBody DTO_queRenWenZhenChuFang dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_queRenWenZhenChuFang {
        UUID wenZhenId;
        Map<String, Object> chuFangNeiRong;
    }
    // command end
}
