package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/yiHuRenYuan")
@Api(tags="医护人员操作")
public class YiHuRenYuanController {

    @Autowired
    private PPCommandGateway ppCommandGateway;

    // query
    @ApiOperation(value = "获取问诊")
    @GetMapping("/huoQuWenZhen/{wenZhenId}")
    public PPResult<WenZhenView> huoQuWenZhen(
            @PathVariable UUID wenZhenDanId
    ) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        return null;
    }

    // query end

    // command
    @ApiOperation(value = "创建问诊报告")
    @PostMapping("/chuangJianWenZhenBaoGao")
    public PPResult chuangJianWenZhen(@Valid @RequestBody DTO_chuangJianWenZhenBaoGao dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_KaiJuWenZhenBaoGaoCmd(
                        dto.getWenZhenId(),
                        dto.getZhengWen(),
                        zhangHaoId
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_chuangJianWenZhenBaoGao {
        @NotNull
        UUID wenZhenId;
        @NotBlank
        String zhengWen;
    }

    @ApiOperation(value = "编辑问诊健康档案")
    @PostMapping("/bianJiWenZhenJianKangDangAn")
    public PPResult bianJiWenZhenJianKangDangAn(@Valid @RequestBody DTO_bianJiWenZhenJianKangDangAn dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end
        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinJianKangDangAnCmd(
                        dto.getWenZhenId(),
                        dto.getJianKangDangAnNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenJianKangDangAn {
        @NotNull
        UUID wenZhenId;
        @NotNull
        Map<String, Object> jianKangDangAnNeiRong;
    }

    @ApiOperation(value = "编辑问诊主要诊断")
    @PostMapping("/bianJiWenZhenZhuYaoZhenDuan")
    public PPResult bianJiWenZhenZhuYaoZhenDuan(@Valid @RequestBody DTO_bianJiWenZhenZhuYaoZhenDuan dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
                        dto.getWenZhenId(),
                        dto.getZhuYaoZhenDuan()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenZhuYaoZhenDuan {
        @NotNull
        UUID wenZhenId;
        @NotBlank
        String zhuYaoZhenDuan;
    }

    @ApiOperation(value = "编辑问诊详细治疗经过")
    @PostMapping("/bianJiWenZhenXiangXiZhiLiaoJingGuo")
    public PPResult bianJiWenZhenXiangXiZhiLiaoJingGuo(@Valid @RequestBody DTO_bianJiWenZhenXiangXiZhiLiaoJingGuo dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd(
                        dto.getWenZhenId(),
                        dto.getXiangXiZhiLiaoJingGuoNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenXiangXiZhiLiaoJingGuo {
        @NotNull
        UUID wenZhenId;
        @NotNull
        Map<String, Object> xiangXiZhiLiaoJingGuoNeiRong;
    }

    @ApiOperation(value = "编辑问诊检查总结")
    @PostMapping("/bianJiWenZhenJianChaZongJie")
    public PPResult bianJiWenZhenJianChaZongJie(@Valid @RequestBody DTO_bianJiWenZhenJianChaZongJie dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinJianChaZongJieCmd(
                        dto.getWenZhenId(),
                        dto.getJianChaZongJieNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenJianChaZongJie {
        @NotNull
        UUID wenZhenId;
        @NotNull
        Map<String, Object> jianChaZongJieNeiRong;
    }

    @ApiOperation(value = "编辑问诊电子影像")
    @PostMapping("/bianJiWenZhenDianZiYingXiang")
    public PPResult bianJiWenZhenDianZiYingXiang(@Valid @RequestBody DTO_bianJiWenZhenDianZiYingXiang dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinDianZiYingXiangCmd(
                        dto.getWenZhenId(),
                        dto.getDianZiYingXiangNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenDianZiYingXiang {
        @NotNull
        UUID wenZhenId;
        @NotNull
        Map<String, Object> dianZiYingXiangNeiRong;
    }

    @ApiOperation(value = "编辑问诊其他材料")
    @PostMapping("/bianJiWenZhenQiTaCaiLiao")
    public PPResult bianJiWenZhenQiTaCaiLiao(@Valid @RequestBody DTO_bianJiWenZhenQiTaCaiLiao dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinQiTaCaiLiaoCmd(
                        dto.getWenZhenId(),
                        dto.getQiTaCaiLiaoNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenQiTaCaiLiao {
        @NotNull
        UUID wenZhenId;
        @NotNull
        Map<String, Object> qiTaCaiLiaoNeiRong;
    }

    @ApiOperation(value = "编辑问诊总结")
    @PostMapping("/bianJiWenZhenZongJie")
    public PPResult bianJiWenZhenZongJie(@Valid @RequestBody DTO_bianJiWenZhenZongJie dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_GengXinWenZhenZongJieCmd(
                        dto.getWenZhenId(),
                        dto.getZongJieNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiWenZhenZongJie {
        @NotNull
        UUID wenZhenId;
        @NotNull
        Map<String, Object> zongJieNeiRong;
    }

    @ApiOperation(value = "开具问诊处方")
    @PostMapping("/kaiJuWenZhenChuFang")
    public PPResult kaiJuWenZhenChuFang(@Valid @RequestBody DTO_kaiJuWenZhenChuFang dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_KaiJuChuFangCmd(
                        dto.getWenZhenId(),
                        dto.getChuFangNeiRong(),
                        zhangHaoId
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_kaiJuWenZhenChuFang {
        @NotNull
        UUID wenZhenId;
        @NotBlank
        String chuFangNeiRong;
    }

    @ApiOperation(value = "确认问诊处方")
    @PostMapping("/queRenWenZhenChuFang")
    public PPResult queRenWenZhenChuFang(@Valid @RequestBody DTO_queRenWenZhenChuFang dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yiHuRenYuanZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_QueRenChuFangCmd(
                        dto.getWenZhenId(),
                        zhangHaoId
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_queRenWenZhenChuFang {
        @NotNull
        UUID wenZhenId;
    }
    // command end
}
