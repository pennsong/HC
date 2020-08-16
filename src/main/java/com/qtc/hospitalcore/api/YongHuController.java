package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.chongFuJianCe.YongHuOpenIdRepository;
import com.qtc.hospitalcore.domain.chongFuJianCe.YongHuShouJiHaoRepository;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.query.YongHuViewRepository;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ChuangJianJiBenXinXiCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/yongHu")
@Api(tags = "用户操作")
public class YongHuController {
    public static final String YONG_HU_YAN_ZHENG_MA_KEY = "yongHuYanZhengMa";
    public static final String YONG_HU_SHOU_JI_HAO_KEY = "yongHuShouJiHao";

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private YongHuShouJiHaoRepository yongHuShouJiHaoRepository;

    @Autowired
    private YongHuOpenIdRepository yongHuOpenIdRepository;

    @Autowired
    YongHuViewRepository yongHuViewRepository;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    // query

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/huoQuChanPinLB")
    public PPResult<List<ChanPinView>> huoQuChanPinLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
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
    @ApiOperation(value = "获取用户手机验证码")
    @PostMapping("/huoQuYongHuShouJiYanZhengMa")
    public PPResult huoQuYongHuShouJiYanZhengMa(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody DTO_huoQuYongHuShouJiYanZhengMa dto) {
        // 校验
        // 检测验证码
        String code = String.format("%04d", new Random().nextInt(10000));
        sessionStrategy.setAttribute(new ServletWebRequest(request), YONG_HU_YAN_ZHENG_MA_KEY, code);
        sessionStrategy.setAttribute(new ServletWebRequest(request), YONG_HU_SHOU_JI_HAO_KEY, dto.getShouJiHao());

        return PPResult.getPPResultOK(code);
    }

    @Data
    static class DTO_huoQuYongHuShouJiYanZhengMa {
        @NotBlank
        String shouJiHao;
    }

    // 这里不可以用@Transactional, 因为检查重复的sql语句需要及时执行, 不能等到transaction递交的时候再执行
    @ApiOperation(value = "递交用户手机验证码")
    @PostMapping("/diJiaoYongHuShouJiYanZhengMa")
    public PPResult diJiaoYongHuShouJiYanZhengMa(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody DTO_diJiaoYongHuShouJiYanZhengMa dto) {
        // 校验
        // 检测验证码
        Object code = sessionStrategy.getAttribute(new ServletWebRequest(request), YONG_HU_YAN_ZHENG_MA_KEY);
        if (code == null) {
            throw new PPBusinessException("验证码已过期或没有获取过验证码");
        }

        if (!(code.toString().equals(dto.yanZhengMa))) {
            throw new PPBusinessException("验证码不匹配");
        }

        // 校验 end
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ExtChuangJianYongHuCmd(
                                UUID.randomUUID(),
                                "s1",
                                "o1"
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_diJiaoYongHuShouJiYanZhengMa {
        @NotBlank
        String shouJiHao;
        @NotBlank
        String yanZhengMa;
        @NotBlank
        String weiXinOpenId;
    }

    @ApiOperation(value = "递交基本信息")
    @PostMapping("/diJiaoJiBenXinXi")
    public PPResult diJiaoJiBenXinXi(@Valid @RequestBody DTO_diJiaoJiBenXinXi dto) {
        commandGateway.sendAndWait(new YongHu_ChuangJianJiBenXinXiCmd(
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

