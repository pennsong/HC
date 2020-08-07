package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.WenZhen;
import com.qtc.hospitalcore.domain.YiHuRenYuan;
import com.qtc.hospitalcore.domain.query.WenZhenView;
import com.qtc.hospitalcore.domain.query.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.query.YongHuView;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/yiHuRenYuan")
public class YiHuRenYuanController {
    // query
    /**
     * 问诊记录
     */
    @GetMapping("/wenZhen/{id}")
    public PPResult<WenZhenView> wenZhen(@PathVariable UUID id) {
        // TODO: PP

        return null;
    }

    // query end

    // command
    /**
     * 创建问诊报告
     */
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

    /**
     * 编辑问诊健康档案
     */
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

    /**
     * 编辑问诊主要诊断
     */
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

    /**
     * 编辑问诊详细治疗经过
     */
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

    /**
     * 编辑问诊检查总结
     */
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

    /**
     * 编辑问诊电子影像
     */
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

    /**
     * 编辑问诊其他材料
     */
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

    /**
     * 编辑问诊总结
     */
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

    /**
     * 开具问诊处方
     */
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

    /**
     * 确认问诊处方
     */
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
