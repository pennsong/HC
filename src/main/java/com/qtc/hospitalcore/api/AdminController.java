package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.ExtChuangJianYiHuRenYuanCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.WenZhen;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_ShanChuCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_SheZhiMiMaCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin操作")
public class AdminController {

    @Autowired
    private CommandGateway commandGateway;
    // query


    // query end

    // command
    @ApiOperation(value = "创建产品")
    @PostMapping("/chuangJianChanPin")
    public PPResult<UUID> chuangJianChanPin(@Valid @RequestBody DTO_chuangJianChanPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_chuangJianChanPin {
        String chanPingMing;
        String daLeiXing;
        String xiaoLeiXing;
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
        String daLeiXing;
        String xiaoLeiXing;
        String chanPingMing;
        BigDecimal yuFuFei;
        BigDecimal shiChangJia;
    }

    @ApiOperation(value = "下架产品")
    @PostMapping("/xiaJiaChanPin")
    public PPResult xiaJiaChanPin(@Valid @RequestBody DTO_xiaJiaChanPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_xiaJiaChanPin {
        UUID changPinId;
    }

    @ApiOperation(value = "上架产品")
    @PostMapping("/shangJiaChanPin")
    public PPResult shangJiaChanPin(@Valid @RequestBody DTO_shangJiaChanPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_shangJiaChanPin {
        UUID changPinId;
    }

    @ApiOperation(value = "删除产品")
    @PostMapping("/shanChuChanPin")
    public PPResult shanChuChanPin(@Valid @RequestBody DTO_shanChuChanPin dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_shanChuChanPin {
        UUID changPinId;
    }

    @ApiOperation(value = "创建排班")
    @PostMapping("/chuangJianPaiBan")
    public PPResult<UUID> chuangJianPaiBan(@Valid @RequestBody DTO_chuangJianPaiBan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_chuangJianPaiBan {
        String chanPingMing;
        String daLeiXing;
        String xiaoLeiXing;
        BigDecimal yuFuFei;
        BigDecimal shiChangJia;
    }

    @ApiOperation(value = "编辑排班")
    @PostMapping("/bianJiPaiBan")
    public PPResult bianJiPaiBan(@Valid @RequestBody DTO_bianJiPaiBan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiPaiBan {
        UUID PaiBanId;
        String daLeiXing;
        String xiaoLeiXing;
        String chanPingMing;
        BigDecimal yuFuFei;
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
        Set<YiHuRenYuan.QuanXian> quanXianZu;
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
