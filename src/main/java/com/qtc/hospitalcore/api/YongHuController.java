package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.query.YongHuViewRepository;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yonghu.ChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.yonghu.DiJiaoJiBenXinXiCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/yongHu")
@Api(tags="用户操作")
public class YongHuController {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    YongHuViewRepository yongHuViewRepository;

    // query

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/huoQuChanPinLB")
    public PPResult<List<ChangPinView>> huoQuChanPinLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/huoQuYongHuXinXi")
    public PPResult<YongHuView> huoQuYongHuXinXi() {

        // TODO: PP
        // authentication中取得当前yongHuId

//        YongHuView result = yongHuViewRepository.findById(yongHuId).get();

        return null;
    }

    @ApiOperation(value = "获取用户健康档案列表")
    @GetMapping("/huoQuYongHuJianKangDangAnLB")
    public PPResult<List<JianKangDangAnView>> huoQuYongHuJianKangDangAnLB(
    ) {
        // TODO: PP
        // authentication中取得当前yongHuId

        return null;
    }

    @ApiOperation(value = "获取用户问诊列表")
    @GetMapping("/huoQuYongHuWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuYongHuWenZhenLB(
    ) {
        // TODO: PP
        // authentication中取得当前yongHuId

        return null;
    }

    @ApiOperation(value = "获取问诊")
    @GetMapping("/huoQuWenZhen/{wenZhenId}")
    public PPResult<WenZhenView> huoQuWenZhen(
            @PathVariable UUID wenZhenDanId
    ) {
        // TODO: PP
        // session取yongHuId

        return null;
    }

    @ApiOperation(value = "下载诊疗报告")
    @GetMapping("/xiaZaiZhenLiaoBaoGao/{wenZhenId}")
    public DTO_RxiaZaiZhenLiaoBaoGao_R xiaZaiZhenLiaoBaoGao(
            @PathVariable UUID wenZhenId
    ) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_RxiaZaiZhenLiaoBaoGao_R {
        // TODO: PP 所有报告下载成pdf格式
    }

    @ApiOperation(value = "下载处方单")
    @GetMapping("/xiaZaiChuFangDan/{wenZhenId}")
    public DTO_RXiaZaiChuFangDan_R xiaZaiChuFangDan(
            @PathVariable UUID wenZhenId
    ) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_RXiaZaiChuFangDan_R {
        // TODO: PP 所有处方单下载成pdf格式
    }
    // query end

    // command
    @ApiOperation(value = "递交基本信息")
    @PostMapping("/diJiaoJiBenXinXi")
    public PPResult diJiaoJiBenXinXi(@Valid @RequestBody DTO_diJiaoJiBenXinXi dto) {
        commandGateway.sendAndWait(new DiJiaoJiBenXinXiCmd(
                        dto.getYongHuId(),
                        dto.getXingMing(),
                        dto.getShenFenZheng(),
                        dto.getJiBenXinXiNeiRong()
                )
        );
        return PPResult.getPPOK();
    }

    @Data
    static class DTO_diJiaoJiBenXinXi {
        UUID yongHuId;
        String shouJiHao;
        String xingMing;
        String shenFenZheng;
        Map<String, Object> jiBenXinXiNeiRong;
    }

    @ApiOperation(value = "发起问诊")
    @PostMapping("/faQiWenZhen")
    public PPResult faQiWenZhen(@Valid @RequestBody DTO_faQiWenZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_faQiWenZhen {
        Map<String, Object> wenZhenNeiRong;
    }

    @ApiOperation(value = "取消问诊")
    @PostMapping("/quXiaoWenZhen")
    public PPResult quXiaoWenZhen(@Valid @RequestBody DTO_quXiaoWenZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_quXiaoWenZhen {
        UUID wenZhenDanId;
    }

    @ApiOperation(value = "支付预付款")
    @PostMapping("/zhiFuYuFuKuan")
    public PPResult zhiFuYuFuKuan(@Valid @RequestBody DTO_zhiFuYuFuKuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_zhiFuYuFuKuan {
        UUID wenZhenDanId;
    }

    @ApiOperation(value = "获取健康档案手机绑定验证码")
    @PostMapping("/huoQuJianKangDangAnShouJiBangDingYanZhengMa")
    public PPResult<String> huoQuJianKangDangAnShouJiBangDingYanZhengMa(@Valid @RequestBody DTO_huoQuJianKangDangAnShouJiBangDingYanZhengMa dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJianKangDangAnShouJiBangDingYanZhengMa {
        String shouJiHaoMa;
    }

    @ApiOperation(value = "创建健康档案")
    @PostMapping("/chuangJianJianKangDangAn")
    public PPResult chuangJianJianKangDangAn(@Valid @RequestBody DTO_chuangJianJianKangDangAn dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_chuangJianJianKangDangAn {
        Map<String, Object> shenFenXinXiNeiRong;
    }

    @ApiOperation(value = "编辑健康信息")
    @PostMapping("/bianJiJianKangXinXi")
    public PPResult bianJiJianKangXinXi(@Valid @RequestBody DTO_bianJiJianKangXinXi dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_bianJiJianKangXinXi {
        Map<String, Object> jianKangXinXiNeiRong;
    }
    // command end
}

