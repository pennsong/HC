package com.qtc.hospitalcore.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.chanpin.ChanPinViewRepository;
import com.qtc.hospitalcore.domain.chongFuJianCe.YongHuOpenIdRepository;
import com.qtc.hospitalcore.domain.chongFuJianCe.YongHuShouJiHaoRepository;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnViewRepository;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAn_ChuangJianCmd;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAn_GengXinJianKangXinXiCmd;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ChuangJianCmd;
import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhen_ChuangJianCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHuViewRepository;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ChuangJianJiBenXinXiCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.GenericCommandMessage;
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
import javax.validation.constraints.NotNull;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/yongHu")
@Api(tags = "用户操作")
public class YongHuController {
    public static final String YONG_HU_YAN_ZHENG_MA_KEY = "yongHuYanZhengMa";
    public static final String YONG_HU_SHOU_JI_HAO_KEY = "yongHuShouJiHao";

    public static final String JIAN_KANG_DANG_AN_YONG_HU_YAN_ZHENG_MA_KEY = "jianKangDangAnYongHuYanZhengMa";
    public static final String JIAN_KANG_DANG_AN_YONG_HU_SHOU_JI_HAO_KEY = "jianKangDangAnYongHuShouJiHao";

    @Autowired
    private PPCommandGateway ppCommandGateway;

    @Autowired
    private YongHuShouJiHaoRepository yongHuShouJiHaoRepository;

    @Autowired
    private YongHuOpenIdRepository yongHuOpenIdRepository;

    @Autowired
    YongHuViewRepository yongHuViewRepository;

    @Autowired
    ZhangHaoViewRepository zhangHaoViewRepository;

    @Autowired
    ChanPinViewRepository chanPinViewRepository;

    @Autowired
    JianKangDangAnViewRepository jianKangDangAnViewRepository;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    // query

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/huoQuChanPinLB")
    public PPResult<List<ChanPinView>> huoQuChanPinLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        return null;
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/huoQuYongHuXinXi")
    public PPResult<YongHuView> huoQuYongHuXinXi() {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        return null;
    }

    @ApiOperation(value = "获取用户健康档案列表")
    @GetMapping("/huoQuYongHuJianKangDangAnLB")
    public PPResult<List<JianKangDangAnView>> huoQuYongHuJianKangDangAnLB(
    ) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end


        return null;
    }

    @ApiOperation(value = "获取用户问诊列表")
    @GetMapping("/huoQuYongHuWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuYongHuWenZhenLB(
    ) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end


        return null;
    }

    @ApiOperation(value = "获取问诊")
    @GetMapping("/huoQuWenZhen/{wenZhenId}")
    public PPResult<WenZhenView> huoQuWenZhen(
            @PathVariable UUID wenZhenDanId
    ) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end


        return null;
    }

    @ApiOperation(value = "下载诊疗报告")
    @GetMapping("/xiaZaiZhenLiaoBaoGao/{wenZhenId}")
    public DTO_RxiaZaiZhenLiaoBaoGao_R xiaZaiZhenLiaoBaoGao(
            @PathVariable UUID wenZhenId
    ) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end


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
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end


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
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        String code = String.format("%04d", new Random().nextInt(10000));
        sessionStrategy.setAttribute(new ServletWebRequest(request), YONG_HU_YAN_ZHENG_MA_KEY, code);
        sessionStrategy.setAttribute(new ServletWebRequest(request), YONG_HU_SHOU_JI_HAO_KEY, dto.getShouJiHao());

        log.info("获取健康档案手机绑定验证码:" + code);

        return PPResult.getPPOK();
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
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        UUID yongHuId = zhangHaoViewRepository.findById(zhangHaoId).get().getYongHuId();
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end
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
        ppCommandGateway.sendAndWait(
                new ExtChuangJianYongHuCmd(
                        zhangHaoId,
                        yongHuId,
                        dto.getShouJiHao(),
                        dto.getWeiXinOpenId()
                )
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
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end
        ppCommandGateway.sendAndWait(
                new YongHu_ChuangJianJiBenXinXiCmd(
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
        @NotNull
        UUID yongHuId;
        @NotBlank
        String xingMing;
        @NotBlank
        String shenFenZheng;
        @NotNull
        Map<String, Object> jiBenXinXiNeiRong;
    }

    @ApiOperation(value = "发起问诊")
    @PostMapping("/faQiWenZhen")
    public PPResult faQiWenZhen(@Valid @RequestBody DTO_faQiWenZhen dto) throws JsonProcessingException {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查

        // 健康档案信息
        Optional<JianKangDangAnView> optionalJianKangDangAnView = jianKangDangAnViewRepository.findById(dto.getJianKangDangAnId());
        if (!(optionalJianKangDangAnView.isPresent())) {
            throw new PPBusinessException("健康档案不存在");
        }

        JianKangDangAnView jianKangDangAnView = optionalJianKangDangAnView.get();
        // TODO: PP 检查健康档案是属于当前用户的

        // 产品信息
        Optional<ChanPinView> optionalChanPinView = chanPinViewRepository.findById(dto.getChanPinId());

        if (!(optionalChanPinView.isPresent())) {
            throw new PPBusinessException("产品不存在");
        }

        ChanPinView chanPinView = optionalChanPinView.get();
        // 参数相关检查 end

        UUID id = UUID.randomUUID();

        Map<String, Object> jianKangDangAnMap = new HashMap<>();
        jianKangDangAnMap.put("id", jianKangDangAnView.getId());
        jianKangDangAnMap.put("zhuangTai", jianKangDangAnView.getZhuangTai());
        jianKangDangAnMap.put("xingMing", jianKangDangAnView.getXingMing());
        jianKangDangAnMap.put("shenFenZhengHao", jianKangDangAnView.getShenFenZhengHao());
        jianKangDangAnMap.put("shouJiHao", jianKangDangAnView.getShouJiHao());
        jianKangDangAnMap.put("jiBenXinXiMap", jianKangDangAnView.getJiBenXinXiMap());
        jianKangDangAnMap.put("jianKangXinXiMap", jianKangDangAnView.getJianKangXinXiMap());

        ppCommandGateway.sendAndWait(
                new WenZhen_ChuangJianCmd(
                        id,
                        dto.getJianKangDangAnId(),
                        dto.getChanPinId(),
                        null,
                        chanPinView.getYuFuFei(),
                        chanPinView.getShiChangJia(),
                        chanPinView.getMingCheng(),
                        PPUtil.objectToJsonString(chanPinView),
                        null,
                        jianKangDangAnMap
                )
        );

        return PPResult.getPPResultOK(id);
    }

    @Data
    static class DTO_faQiWenZhen {
        @NotNull
        UUID chanPinId;
        @NotNull
        UUID jianKangDangAnId;
    }

    @ApiOperation(value = "取消问诊")
    @PostMapping("/quXiaoWenZhen")
    public PPResult quXiaoWenZhen(@Valid @RequestBody DTO_quXiaoWenZhen dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end
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
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_zhiFuYuFuKuan {
        UUID wenZhenDanId;
    }

    @ApiOperation(value = "获取健康档案手机绑定验证码")
    @PostMapping("/huoQuJianKangDangAnShouJiBangDingYanZhengMa")
    public PPResult huoQuJianKangDangAnShouJiBangDingYanZhengMa(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody DTO_huoQuJianKangDangAnShouJiBangDingYanZhengMa dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end
        String code = String.format("%04d", new Random().nextInt(10000));
        sessionStrategy.setAttribute(new ServletWebRequest(request), JIAN_KANG_DANG_AN_YONG_HU_YAN_ZHENG_MA_KEY, code);
        sessionStrategy.setAttribute(new ServletWebRequest(request), JIAN_KANG_DANG_AN_YONG_HU_SHOU_JI_HAO_KEY, dto.getShouJiHao());

        log.info("获取健康档案手机绑定验证码:" + code);

        return PPResult.getPPOK();
    }

    @Data
    static class DTO_huoQuJianKangDangAnShouJiBangDingYanZhengMa {
        @NotBlank
        String shouJiHao;
    }

    @ApiOperation(value = "创建健康档案")
    @PostMapping("/chuangJianJianKangDangAn")
    public PPResult chuangJianJianKangDangAn(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody DTO_chuangJianJianKangDangAn dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 检测验证码
        Object code = sessionStrategy.getAttribute(new ServletWebRequest(request), JIAN_KANG_DANG_AN_YONG_HU_YAN_ZHENG_MA_KEY);
        if (code == null) {
            throw new PPBusinessException("验证码已过期或没有获取过验证码");
        }

        if (!(code.toString().equals(dto.yanZhengMa))) {
            throw new PPBusinessException("验证码不匹配");
        }

        // 验证身份证格式
        // TODO: PP
        // 验证手机格式
        // TODO: PP

        // TODO: PP 需要验证shenFenXinXiNeiRong中的地址有没有么?
        // 参数相关检查 end

        UUID id = UUID.randomUUID();

        // TODO: PP实现登录后替换掉
        UUID yongHuId = PPUtil.yongHuZhangHaoId;

        ppCommandGateway.sendAndWait(
                new JianKangDangAn_ChuangJianCmd(
                        id,
                        yongHuId,
                        dto.getXingMing(),
                        dto.getShenFenZhengHao(),
                        dto.getShouJiHao(),
                        dto.getShenFenXinXiNeiRong()
                )
        );

        return PPResult.getPPResultOK(id);
    }

    @Data
    static class DTO_chuangJianJianKangDangAn {
        @NotBlank
        String xingMing;
        @NotBlank
        String shenFenZhengHao;
        @NotBlank
        String shouJiHao;
        @NotNull
        Map<String, Object> shenFenXinXiNeiRong;
        @NotBlank
        String yanZhengMa;
    }

    @ApiOperation(value = "编辑健康信息")
    @PostMapping("/bianJiJianKangXinXi")
    public PPResult bianJiJianKangXinXi(@Valid @RequestBody DTO_bianJiJianKangXinXi dto) {
        // TODO: PP authentication中取得当前zhangHaoId
        UUID zhangHaoId = PPUtil.yongHuZhangHaoId;
        // TODO: PP end

        // 参数相关检查
        // 参数相关检查 end

        UUID id = UUID.randomUUID();

        ppCommandGateway.sendAndWait(
                new JianKangDangAn_GengXinJianKangXinXiCmd(
                        id,
                        dto.getJianKangXinXiNeiRong()
                )
        );

        return PPResult.getPPResultOK(id);
    }

    @Data
    static class DTO_bianJiJianKangXinXi {
        @NotNull
        Map<String, Object> jianKangXinXiNeiRong;
    }
    // command end
}

