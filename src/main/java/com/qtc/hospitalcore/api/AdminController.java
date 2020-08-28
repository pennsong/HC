package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.ExtChuangJianPaiBanCmd;
import com.qtc.hospitalcore.domain.ExtJianChaChanPinCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYiHuRenYuanCmd;
import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ChuangJianCmd;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ShangJiaEvt;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ShouChuCmd;
import com.qtc.hospitalcore.domain.paiban.PaiBan_XiaJiaCmd;
import com.qtc.hospitalcore.domain.util.BiZhong;
import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.*;
import com.qtc.hospitalcore.domain.yihurenyuan.QuanXian;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan_GengXinCmd;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan_SheZhiQuanXianCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ShanChuCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_ShanChuCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_SheZhiMiMaCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventStore;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin操作")
public class AdminController {
    @Autowired
    private PPCommandGateway ppCommandGateway;

    @Autowired
    private ChanPinViewRepository chanPinViewRepository;
    // query


    // query end

    // command
    @ApiOperation(value = "创建产品")
    @PostMapping("/chuangJianChanPin")
    public PPResult<UUID> chuangJianChanPin(@Valid @RequestBody DTO_chuangJianChanPin dto) {
        // 参数相关检查
        // 参数相关检查 end

        UUID id = UUID.randomUUID();

        ppCommandGateway.sendAndWait(
                new ChanPin_ChuangJianCmd(
                        id,
                        dto.getChanPingMing(),
                        dto.getDaLeiXing(),
                        dto.getXiaoLeiXing(),
                        dto.getYuFuFei(),
                        dto.getShiChangJia(),
                        dto.getXinXiMap()
                )
        );

        return PPResult.getPPResultOK(id);
    }

    @Data
    static class DTO_chuangJianChanPin {
        @NotBlank
        String chanPingMing;
        @NotBlank
        String daLeiXing;
        @NotBlank
        String xiaoLeiXing;
        @NotNull
        @Min(0)
        BigDecimal yuFuFei;
        @NotNull
        @Min(0)
        BigDecimal shiChangJia;
        @NotNull
        Map<String, Object> xinXiMap;
    }

    @ApiOperation(value = "编辑产品")
    @PostMapping("/bianJichanPin")
    public PPResult bianJichanPin(@Valid @RequestBody DTO_bianJichanPin dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new ChanPin_GengXinCmd(
                        dto.getChanPinId(),
                        dto.getChanPingMing(),
                        dto.getDaLeiXing(),
                        dto.getXiaoLeiXing(),
                        dto.getYuFuFei(),
                        dto.getShiChangJia(),
                        dto.getXinXiMap()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJichanPin {
        @NotNull
        UUID chanPinId;
        @NotBlank
        String chanPingMing;
        @NotBlank
        String daLeiXing;
        @NotBlank
        String xiaoLeiXing;
        @NotNull
        @Min(0)
        BigDecimal yuFuFei;
        @NotNull
        @Min(0)
        BigDecimal shiChangJia;
        @NotNull
        Map<String, Object> xinXiMap;
    }

    @ApiOperation(value = "下架产品")
    @PostMapping("/xiaJiaChanPin")
    public PPResult xiaJiaChanPin(@Valid @RequestBody DTO_xiaJiaChanPin dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new ChanPin_XiaJiaCmd(
                        dto.getChanPinId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_xiaJiaChanPin {
        @NotNull
        UUID chanPinId;
    }

    @ApiOperation(value = "上架产品")
    @PostMapping("/shangJiaChanPin")
    public PPResult shangJiaChanPin(@Valid @RequestBody DTO_shangJiaChanPin dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new ChanPin_ShangJiaCmd(
                        dto.getChanPinId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shangJiaChanPin {
        @NotNull
        UUID chanPinId;
    }

    @ApiOperation(value = "删除产品")
    @PostMapping("/shanChuChanPin")
    public PPResult shanChuChanPin(@Valid @RequestBody DTO_shanChuChanPin dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new ChanPin_ShanChuCmd(
                        dto.getChanPinId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shanChuChanPin {
        @NotNull
        UUID chanPinId;
    }

    @ApiOperation(value = "创建排班")
    @PostMapping("/chuangJianPaiBan")
    public PPResult<UUID> chuangJianPaiBan(@Valid @RequestBody DTO_chuangJianPaiBan dto) {
        // 参数相关检查
        // 检查排班时间
        if (Duration.between(OffsetDateTime.now(), dto.getShiJian()).toDays() > 365) {
            throw new PPBusinessException("只能创建将来365天内的排班");
        }

        // 检查对应产品是否存在并且没有被删除
        Optional<ChanPinView> chanPinView = chanPinViewRepository.findById(dto.getChanPinId());

        if (!chanPinView.isPresent()) {
            throw new PPBusinessException("对应产品不存在");
        }

        // 参数相关检查 end

        UUID id = UUID.randomUUID();

        ppCommandGateway.sendAndWait(
                new PaiBan_ChuangJianCmd(
                        id,
                        dto.getChanPinId(),
                        dto.getYuFuFei(),
                        dto.getShiChangJia(),
                        dto.getYiSheng(),
                        dto.getShiJian(),
                        dto.getXinXiMap()
                )
        );

        return PPResult.getPPResultOK(id);
    }

    @Data
    static class DTO_chuangJianPaiBan {
        @NotNull
        UUID chanPinId;
        @NotNull
        @Min(0)
        BigDecimal yuFuFei;
        @NotNull
        @Min(0)
        BigDecimal shiChangJia;
        @NotBlank
        String yiSheng;
        @NotNull
        @Future
        OffsetDateTime shiJian;
        @NotNull
        Map<String, Object> xinXiMap;
    }

    @ApiOperation(value = "下架排班")
    @PostMapping("/xiaJiaPaiBan")
    public PPResult xiaJiaPaiBan(@Valid @RequestBody DTO_xiaJiaPaiBan dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new PaiBan_XiaJiaCmd(
                        dto.getPaiBanId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_xiaJiaPaiBan {
        @NotNull
        UUID PaiBanId;
    }

    @ApiOperation(value = "上架排班")
    @PostMapping("/shangJiaPaiBan")
    public PPResult shangJiaPaiBan(@Valid @RequestBody DTO_shangJiaPaiBan dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new PaiBan_ShangJiaEvt(
                        dto.getPaiBanId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shangJiaPaiBan {
        @NotNull
        UUID PaiBanId;
    }

    @ApiOperation(value = "删除排班")
    @PostMapping("/shanChuPaiBan")
    public PPResult shanChuPaiBan(@Valid @RequestBody DTO_shanChuPaiBan dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new PaiBan_ShouChuCmd(
                        dto.getPaiBanId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shanChuPaiBan {
        @NotNull
        UUID PaiBanId;
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/shanChuYongHu")
    public PPResult shanChuYongHu(@Valid @RequestBody DTO_shanChuYongHu dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new YongHu_ShanChuCmd(
                        dto.getYongHuId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shanChuYongHu {
        @NotNull
        UUID yongHuId;
    }

    @ApiOperation(value = "创建医护人员")
    @PostMapping("/chuangJianYiHuRenYuan")
    public PPResult<UUID> chuangJianYiHuRenYuan(@Valid @RequestBody DTO_chuangJianYiHuRenYuan dto) {
        // 参数相关检查
        // 参数相关检查 end

        UUID id = UUID.randomUUID();
        UUID yiHuRenYuanId = UUID.randomUUID();
        ppCommandGateway.sendAndWait(
                new ExtChuangJianYiHuRenYuanCmd(
                        id,
                        yiHuRenYuanId,
                        dto.getDengLuMing(),
                        dto.getDengLuMiMa(),
                        dto.getXingMing(),
                        dto.getShenFenZheng(),
                        dto.getYiHuRenYuanXinXiNeiRong()
                )
        );

        return PPResult.getPPResultOK(id);
    }

    @Data
    static class DTO_chuangJianYiHuRenYuan {
        @NotBlank
        String dengLuMing;
        @NotBlank
        String dengLuMiMa;
        @NotBlank
        String xingMing;
        @NotBlank
        String shenFenZheng;
        @NotNull
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @ApiOperation(value = "删除医护人员帐号")
    @PostMapping("/shanChuYiHuRenYuanZhangHao")
    public PPResult shanChuYiHuRenYuanZhangHao(@Valid @RequestBody DTO_shanChuYiHuRenYuanZhangHao dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new ZhangHao_ShanChuCmd(
                        dto.getYiHuRenYuanZhangHaoId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shanChuYiHuRenYuanZhangHao {
        @NotNull
        UUID yiHuRenYuanZhangHaoId;
    }

    @ApiOperation(value = "编辑医护人员")
    @PostMapping("/bianJiYiHuRenYuan")
    public PPResult bianJiYiHuRenYuan(@Valid @RequestBody DTO_bianJiYiHuRenYuan dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new YiHuRenYuan_GengXinCmd(
                        dto.getYiHuRenYuanId(),
                        dto.getXingMing(),
                        dto.getShenFenZheng(),
                        dto.getYiHuRenYuanXinXiNeiRong()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_bianJiYiHuRenYuan {
        @NotNull
        UUID yiHuRenYuanId;
        @NotBlank
        String xingMing;
        @NotBlank
        String shenFenZheng;
        @NotNull
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @ApiOperation(value = "设置医护人员权限")
    @PostMapping("/sheZhiYiHuRenYuanQuanXian")
    public PPResult sheZhiYiHuRenYuanQuanXian(@Valid @RequestBody DTO_sheZhiYiHuRenYuanQuanXian dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new YiHuRenYuan_SheZhiQuanXianCmd(
                        dto.getYiHuRenYuanId(),
                        dto.getQuanXianZu()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_sheZhiYiHuRenYuanQuanXian {
        @NotNull
        UUID yiHuRenYuanId;
        @NotNull
        Map<QuanXian, Boolean> quanXianZu;
    }

    @ApiOperation(value = "设置医护人员密码")
    @PostMapping("/sheZhiYiHuRenYuanMiMa")
    public PPResult sheZhiYiHuRenYuanMiMa(@Valid @RequestBody DTO_sheZhiYiHuRenYuanMiMa dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new ZhangHao_SheZhiMiMaCmd(
                        dto.getYiHuRenYuanZhangHaoId(),
                        dto.getMiMa()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_sheZhiYiHuRenYuanMiMa {
        @NotNull
        UUID yiHuRenYuanZhangHaoId;
        @NotBlank
        String miMa;
    }

    @ApiOperation(value = "结束问诊")
    @PostMapping("/jieShuWenZhen")
    public PPResult jieShuWenZhen(@Valid @RequestBody DTO_jieShuWenZhen dto) {
        // 参数相关检查
        // 参数相关检查 end

        if (dto.jieGuo == JieGuo.CHENG_GONG) {
            ppCommandGateway.sendAndWait(
                    new WenZhen_ChengGongWanChengCmd(
                            dto.getWenZhenId(),
                            dto.getBeiZhu()
                    )
            );
        } else if (dto.jieGuo == JieGuo.JIE_SHU) {
            ppCommandGateway.sendAndWait(
                    new WenZhen_JieShuWanChengCmd(
                            dto.getWenZhenId(),
                            dto.getBeiZhu()
                    )
            );
        }

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_jieShuWenZhen {
        @NotNull
        UUID wenZhenId;
        @NotNull
        JieGuo jieGuo;
        String beiZhu;
    }

    @ApiOperation(value = "分配问诊")
    @PostMapping("/fenPeiWenZhen")
    public PPResult fenPeiWenZhen(@Valid @RequestBody DTO_fenPeiWenZhen dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_AnPaiYiShengCmd(
                        dto.getWenZhenId(),
                        dto.getWenZhenZhangHaoId(),
                        dto.getBianJiBingLiZhangHaoId(),
                        dto.getKaiJuChuFangZhangHaoId(),
                        dto.getQueRenChuFangZhangHaoId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_fenPeiWenZhen {
        @NotNull
        UUID wenZhenId;
        @NotNull
        UUID wenZhenZhangHaoId;
        @NotNull
        UUID bianJiBingLiZhangHaoId;
        @NotNull
        UUID kaiJuChuFangZhangHaoId;
        @NotNull
        UUID queRenChuFangZhangHaoId;
    }

    @ApiOperation(value = "转诊")
    @PostMapping("/zhuanZhen")
    public PPResult zhuanZhen(@Valid @RequestBody DTO_zhuanZhen dto) {
        // 参数相关检查
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_ZhuanZhenCmd(
                        dto.getWenZhenId(),
                        dto.getWenZhenZhangHaoId(),
                        dto.getBianJiBingLiZhangHaoId(),
                        dto.getKaiJuChuFangZhangHaoId(),
                        dto.getQueRenChuFangZhangHaoId()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_zhuanZhen {
        @NotNull
        UUID wenZhenId;
        @NotNull
        UUID wenZhenZhangHaoId;
        @NotNull
        UUID bianJiBingLiZhangHaoId;
        @NotNull
        UUID kaiJuChuFangZhangHaoId;
        @NotNull
        UUID queRenChuFangZhangHaoId;
    }

    @ApiOperation(value = "安排会诊")
    @PostMapping("/anPaiHuiZhen")
    public PPResult anPaiHuiZhen(@Valid @RequestBody DTO_anPaiHuiZhen dto) {
        // 参数相关检查
        if (Duration.between(OffsetDateTime.now(), dto.getHuiZhenShiJian()).toDays() > 365) {
            throw new PPBusinessException("只能创建将来365天内的会诊");
        }
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_AnPaiHuiZhenCmd(
                        dto.getWenZhenId(),
                        dto.getHuiZhenShiJian(),
                        dto.getLianJie(),
                        dto.getHuiYiId(),
                        dto.getHuanFangCanYuRenYuan(),
                        dto.getBeiZhu()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_anPaiHuiZhen {
        @NotNull
        UUID wenZhenId;
        @NotNull
        @Future
        OffsetDateTime huiZhenShiJian;
        @NotBlank
        String lianJie;
        @NotBlank
        String huiYiId;
        @NotBlank
        String huanFangCanYuRenYuan;
        String beiZhu;

    }

    @ApiOperation(value = "补充问诊付款")
    @PostMapping("/buChongWenZhenFuKuan")
    public PPResult buChongWenZhenFuKuan(@Valid @RequestBody DTO_buChongWenZhenFuKuan dto) {
        // 参数相关检查
        // 补充付款日期
        long days = Duration.between(dto.getBuChongFuKuanRiQi(), OffsetDateTime.now()).toDays();

        if (days > 1000) {
            throw new PPBusinessException("只能录入1000内的付款");
        }

        // 付款凭证
        if (dto.getFuKuanPingZheng().size() == 0) {
            throw new PPBusinessException("凭证不能为空");
        }

        for (String item : dto.getFuKuanPingZheng()) {
            if (StringUtils.isEmpty(item)) {
                throw new PPBusinessException("凭证项目不能为空");
            }
        }
        // 付款凭证 end
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_ZhiFuBuChongKuanCmd(
                        dto.getWenZhenId(),
                        dto.getLiuShuiHao(),
                        dto.getBuChongFuKuanRiQi(),
                        dto.getFuKuanFang(),
                        dto.getBiZhong(),
                        dto.getFuKuanE(),
                        dto.getFuKuanDangRiHuiLv(),
                        dto.getBeiZhu(),
                        dto.getFuKuanPingZheng()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_buChongWenZhenFuKuan {
        @NotNull
        UUID wenZhenId;
        @NotBlank
        String liuShuiHao;
        @NotNull
        @Past
        OffsetDateTime buChongFuKuanRiQi;
        @NotBlank
        String fuKuanFang;
        @NotNull
        @Min(0)
        BigDecimal fuKuanE;
        @NotNull
        BiZhong biZhong;
        @NotNull
        @Min(0)
        Float fuKuanDangRiHuiLv;
        @NotNull
        List<String> fuKuanPingZheng;
        String beiZhu;
    }

    @ApiOperation(value = "发起问诊退款")
    @PostMapping("/faQiWenZhenTuiKuan")
    public PPResult faQiTuiKuan(@Valid @RequestBody DTO_faQiWenZhenTuiKuan dto) {
        // 参数相关检查
        // 补充付款日期
        long days = Duration.between(dto.getTuiKuanRiQi(), OffsetDateTime.now()).toDays();

        if (days > 1000) {
            throw new PPBusinessException("只能录入1000内的退款");
        }

        // 付款凭证
        if (dto.getTuiKuanPingZheng().size() == 0) {
            throw new PPBusinessException("凭证不能为空");
        }

        for (String item : dto.getTuiKuanPingZheng()) {
            if (StringUtils.isEmpty(item)) {
                throw new PPBusinessException("凭证项目不能为空");
            }
        }
        // 付款凭证 end
        // 参数相关检查 end

        ppCommandGateway.sendAndWait(
                new WenZhen_ZhiXingTuiKuanCmd(
                        dto.getWenZhenId(),
                        dto.getLiuShuiHao(),
                        dto.getTuiKuanRiQi(),
                        dto.getShouKuanZhangHuMing(),
                        dto.getShouKuanZhangHu(),
                        dto.getTuiKuanE(),
                        dto.getBeiZhu(),
                        dto.getTuiKuanPingZheng()
                )
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_faQiWenZhenTuiKuan {
        @NotNull
        UUID wenZhenId;
        @NotBlank
        String liuShuiHao;
        @NotNull
        @Past
        OffsetDateTime tuiKuanRiQi;
        @NotBlank
        String shouKuanZhangHuMing;
        @NotBlank
        String shouKuanZhangHu;
        @NotNull
        @Min(0)
        BigDecimal tuiKuanE;
        @NotNull
        List<String> tuiKuanPingZheng;
        String beiZhu;
    }
    // command end
}
