package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.ExtCheckChanPinCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYiHuRenYuanCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.chongFuJianCe.YongHuShouJiHaoRepository;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ChuangJianCmd;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.JieGuo;
import com.qtc.hospitalcore.domain.wenzhen.WenZhen;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yihurenyuan.QuanXian;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewRepository;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_ShanChuCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_SheZhiMiMaCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventStore;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.messaging.unitofwork.CurrentUnitOfWork;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin操作")
public class AdminController {

    @Autowired
    private AxonServerEventStore axonServerEventStore;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private Repository<ChanPin> chanPinRepository;

    // query


    // query end

    // command
    @ApiOperation(value = "创建产品")
    @PostMapping("/chuangJianChanPin")
    public PPResult<UUID> chuangJianChanPin(@Valid @RequestBody DTO_chuangJianChanPin dto) {
        UUID id = UUID.randomUUID();

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ChanPin_ChuangJianCmd(
                                id,
                                dto.getChanPingMing(),
                                dto.getDaLeiXing(),
                                dto.getXiaoLeiXing(),
                                dto.getYuFuFei(),
                                dto.getShiChangJia(),
                                dto.getXinXiMap()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
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

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ChanPin_GengXinCmd(
                                dto.getChanPinId(),
                                dto.getChanPingMing(),
                                dto.getDaLeiXing(),
                                dto.getXiaoLeiXing(),
                                dto.getYuFuFei(),
                                dto.getShiChangJia(),
                                dto.getXinXiMap()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
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
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ChanPin_XiaJiaCmd(
                                dto.getChanPinId()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
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
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ChanPin_ShangJiaCmd(
                                dto.getChanPinId()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
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
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ChanPin_ShanChuCmd(
                                dto.getChanPinId()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
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
        // 对应产品id存在
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ExtCheckChanPinCmd(
                                dto.getChanPinId()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        // 参数相关检查 end

        UUID id = UUID.randomUUID();

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new PaiBan_ChuangJianCmd(
                                id,
                                dto.getChanPinId(),
                                dto.getYuFuFei(),
                                dto.getShiChangJia(),
                                dto.getYiSheng(),
                                dto.getShiJian(),
                                dto.getXinXiMap()

                        )
                ).withMetaData(PPUtil.stringToMap(""))
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

    @ApiOperation(value = "编辑排班")
    @PostMapping("/bianJiPaiBan")
    public PPResult bianJiPaiBan(@Valid @RequestBody DTO_bianJiPaiBan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiPaiBan {
        @NotNull
        UUID PaiBanId;
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
    }

    @ApiOperation(value = "下架排班")
    @PostMapping("/xiaJiaPaiBan")
    public PPResult xiaJiaPaiBan(@Valid @RequestBody DTO_xiaJiaPaiBan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_xiaJiaPaiBan {
        UUID PaiBanId;
    }

    @ApiOperation(value = "上架排班")
    @PostMapping("/shangJiaPaiBan")
    public PPResult shangJiaPaiBan(@Valid @RequestBody DTO_shangJiaPaiBan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_shangJiaPaiBan {
        UUID PaiBanId;
    }

    @ApiOperation(value = "删除排班")
    @PostMapping("/shanChuPaiBan")
    public PPResult shanChuPaiBan(@Valid @RequestBody DTO_shanChuPaiBan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_shanChuPaiBan {
        UUID PaiBanId;
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
        // 校验

        // 校验 end
        UUID result = UUID.randomUUID();

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ExtChuangJianYiHuRenYuanCmd(
                                result,
                                dto.getDengLuMing(),
                                dto.getDengLuMiMa(),
                                dto.getXingMing(),
                                dto.getShenFenZhengHao(),
                                dto.getYiHuRenYuanXinXiNeiRong()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPResultOK(result);
    }

    @Data
    static class DTO_chuangJianYiHuRenYuan {
        String dengLuMing;
        String dengLuMiMa;
        String xingMing;
        String shenFenZhengHao;
        Map<String, Object> yiHuRenYuanXinXiNeiRong;
    }

    @ApiOperation(value = "删除医护人员帐号")
    @PostMapping("/shanChuYiHuRenYuanZhangHao")
    public PPResult shanChuYiHuRenYuanZhangHao(@Valid @RequestBody DTO_shanChuYiHuRenYuanZhangHao dto) {

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ZhangHao_ShanChuCmd(
                                dto.getYiHuRenYuanZhangHaoId()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_shanChuYiHuRenYuanZhangHao {
        UUID yiHuRenYuanZhangHaoId;
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
        Set<QuanXian> quanXianZu;
    }

    @ApiOperation(value = "设置医护人员密码")
    @PostMapping("/sheZhiYiHuRenYuanMiMa")
    public PPResult sheZhiYiHuRenYuanMiMa(@Valid @RequestBody DTO_sheZhiYiHuRenYuanMiMa dto) {
        // 校验

        // 校验 end
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ZhangHao_SheZhiMiMaCmd(
                                dto.getYiHuRenYuanZhangHaoId(),
                                dto.getMiMa()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_sheZhiYiHuRenYuanMiMa {
        UUID yiHuRenYuanZhangHaoId;
        String miMa;
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
        JieGuo jieGuo;
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
