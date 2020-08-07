package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.query.YongHuViewRepository;
import com.qtc.hospitalcore.domain.yonghu.ChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.yonghu.DiJiaoJiBenXinXiCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/yongHu")
public class YongHuController {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    YongHuViewRepository yongHuViewRepository;

    // query

    /**
     * 商品列表
     */
    @GetMapping("/changPin")
    public PPResult<List<ChangPinView>> ChangPin() {
        // TODO: PP

        return null;
    }

    /**
     * 获取信息
     */
    @GetMapping("/huoQuXinXi/{id}")
    public PPResult<YongHuView> huoQuXinXi(@PathVariable UUID id) {

        YongHuView result = yongHuViewRepository.findById(id).get();

        return PPResult.getPPResultOK(result);
    }

    /**
     * 获取健康档案列表
     */
    @GetMapping("/huoQuJianKangDangAnLB")
    public PPResult<List<DTO_RhuoQuJianKangDangAnLB_R>> huoQuJianKangDangAnLB(
    ) {
        // TODO: PP
        // session取yongHuId

        return null;
    }

    @Data
    static class DTO_RhuoQuJianKangDangAnLB_R {
        String xingMing;
        int nianLing;
        String xingBie;
    }

    /**
     * 获取问诊单列表
     */
    @GetMapping("/huoQuWenZhenDanLB")
    public PPResult<List<DTO_RhuoQuWenZhenDanLB_R>> huoQuYongHuWenZhenDanZuLB(
    ) {
        // TODO: PP
        // session取yongHuId

        return null;
    }

    @Data
    static class DTO_RhuoQuWenZhenDanLB_R {
        String wenZhenDanBianHao;
        String zhuangTai;
        String fuFeiZhuangTai;
        String chuFangZhuangTai;
        UUID chanPinId;
        Map<String, Object> yongHuTianXieNeiRong;
    }

    /**
     * 获取问诊单
     */
    @GetMapping("/huoQuWenZhenDan/{wenZhenDanId}")
    public PPResult<List<DTO_RhuoQuWenZhenDan_R>> huoQuYongHuWenZhenDan(
            @PathVariable UUID wenZhenDanId
    ) {
        // TODO: PP
        // session取yongHuId

        return null;
    }

    @Data
    static class DTO_RhuoQuWenZhenDan_R {
        String wenZhenDanBianHao;
        String zhuangTai;
        String fuFeiZhuangTai;
        String chuFangZhuangTai;
        UUID chanPinId;
        // TODO: PP 是否要分 用户填写内容, 各个角色填写内容?
        Map<String, Object> neiRong;
    }

    /**
     * 下载诊疗报告
     */
    @GetMapping("/xiaZaiZhenLiaoBaoGao/{wenZhenDanId}")
    public DTO_RxiaZaiZhenLiaoBaoGao_R xiaZaiZhenLiaoBaoGao(
            @PathVariable UUID wenZhenDanId
    ) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_RxiaZaiZhenLiaoBaoGao_R {
        // TODO: PP 所有报告下载成pdf格式
    }

    /**
     * 下载处方单
     */
    @GetMapping("/xiaZaiChuFangDan/{wenZhenDanId}")
    public DTO_RXiaZaiChuFangDan_R xiaZaiChuFangDan(
            @PathVariable UUID wenZhenDanId
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

    /**
     * 递交基本信息
     */
    @PostMapping("/diJiaoJiBenXinXi")
    public PPResult c(@Valid @RequestBody DTO_diJiaoJiBenXinXi dto) {
        commandGateway.sendAndWait(new DiJiaoJiBenXinXiCmd(
                        dto.getYongHuId(),
                        dto.getShouJiHao(),
                        dto.getXingMing(),
                        dto.getShenFenZheng(),
                        dto.getJiBenXinXiNeiRong()
                )
        );
        return PPResult.getPPOK();
    }

    @Value
    static class DTO_diJiaoJiBenXinXi {
        UUID yongHuId;
        String shouJiHao;
        String xingMing;
        String shenFenZheng;
        Map<String, Object> jiBenXinXiNeiRong;
    }

    /**
     * 发起问诊
     */
    @PostMapping("/faQiWenZhen")
    public PPResult faQiWenZhen(@Valid @RequestBody DTO_faQiWenZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_faQiWenZhen {
        Map<String, Object> wenZhenNeiRong;
    }

    /**
     * 取消问诊
     */
    @PostMapping("/quXiaoWenZhen")
    public PPResult quXiaoWenZhen(@Valid @RequestBody DTO_quXiaoWenZhen dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_quXiaoWenZhen {
        UUID wenZhenDanId;
    }

    /**
     * 支付预付款
     */
    @PostMapping("/zhiFuYuFuKuan")
    public PPResult zhiFuYuFuKuan(@Valid @RequestBody DTO_zhiFuYuFuKuan dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_zhiFuYuFuKuan {
        UUID wenZhenDanId;
    }

    /**
     * 获取健康档案手机绑定验证码
     */
    @PostMapping("/huoQuJianKangDangAnShouJiBangDingYanZhengMa")
    public PPResult huoQuJianKangDangAnShouJiBangDingYanZhengMa(@Valid @RequestBody DTO_huoQuJianKangDangAnShouJiBangDingYanZhengMa dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJianKangDangAnShouJiBangDingYanZhengMa {
        String shouJiHaoMa;
    }

    /**
     * 新建健康档案
     */
    @PostMapping("/xinJianJianKangDangAn")
    public PPResult xinJianJianKangDangAn(@Valid @RequestBody DTO_xinJianJianKangDangAn dto) {
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_xinJianJianKangDangAn {
        Map<String, Object> shenFenXinXiNeiRong;
    }

    /**
     * 编辑健康信息
     */
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

