package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.paiban.PaiBanView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewExt;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/houTai")
@Api(tags = "后台操作", description = "后台通用操作")
public class HouTaiController {
    @Autowired
    private EntityManager em;

    @Autowired
    private ZhangHaoViewRepository zhangHaoViewRepository;

    // query
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/huoQuYongHuLB")
    public PPResult<List<ZhangHaoViewExt>> huoQuYongHuLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {


        Query q = em.createNativeQuery(
                "" +
                        "SELECT " +
                        "   z.id zhang_hao_id," +
                        "   y.id yong_hu_id," +
                        "   y.shou_ji_hao," +
                        "   y.xing_ming," +
                        "   y.shen_fen_zheng," +
                        "   y.wei_xin_open_id," +
                        "   y.xin_xi_map " +
                        "FROM " +
                        "   zhang_hao_view z " +
                        "JOIN " +
                        "   yong_hu_view y " +
                        "ON " +
                        "   z.yong_hu_id = y.id " +
                        "WHERE" +
                        "   z.deleted = false " +
                        "AND" +
                        "   y.deleted = false " +
                        "AND " +
                        "   y.xing_ming LIKE :keyword",
                ZhangHaoViewExt.class
        );
        q.setParameter("keyword", "%" + queryKey + "%");

        q.setFirstResult(new Long(pageable.getOffset()).intValue()).setMaxResults(pageable.getPageSize());

        List<ZhangHaoViewExt> result = q.getResultList();


        return PPResult.getPPResultOK(result);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/huoQuYongHu/{yongHuId}")
    public PPResult<YongHuView> huoQuYongHu(@PathVariable UUID yongHuId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取医护人员列表")
    @GetMapping("/huoQuYiHuRenYuanLB")
    public PPResult<List<YiHuRenYuanView>> huoQuYiHuRenYuanLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取医护人员")
    @GetMapping("/huoQuYiHuRenYuan/{yiHuRenYuanId}")
    public PPResult<YiHuRenYuanView> yiHuRenYuan(@PathVariable UUID yiHuRenYuanId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取问诊列表")
    @GetMapping("/huoQuWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuWenZhenLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取问诊")
    @GetMapping("/huoQuWenZhen/{wenZhenId}")
    public PPResult<WenZhenView> wenZhen(@PathVariable UUID wenZhenId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取药品列表")
    @GetMapping("/huoQuYaoPinLB")
    public PPResult<List<YaoPinView>> huoQuYaoPinLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取药品信息")
    @GetMapping("/huoQuYaoPinXinXi/{yaoPinId}")
    public PPResult<YaoPinView> huoQuYaoPinXinXi(@PathVariable UUID wenZhenId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/huoQuChanPinLB")
    public PPResult<List<ChanPinView>> huoQuChanPinLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取产品")
    @GetMapping("/huoQuChanPin/{chanPinId}")
    public PPResult<ChanPinView> huoQuChanPin(@PathVariable UUID chanPinId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取排班列表")
    @GetMapping("/huoQuPaiBanLB")
    public PPResult<List<PaiBanView>> huoQuPaiBanLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取排班")
    @GetMapping("/huoQuPaiBan/{chanPinId}")
    public PPResult<PaiBanView> huoQuPaiBan(@PathVariable UUID chanPinId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取健康档案列表")
    @GetMapping("/huoQuJianKangDangAnLB")
    public PPResult<List<JianKangDangAnView>> huoQuJianKangDangAnLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取健康档案问诊列表")
    @GetMapping("/huoQuJianKangDangAnWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuJianKangDangAnWenZhenLB(DTO_huoQuJiuZhenRenWenZhenLB dto) {
        // 参数相关检查
        // 参数相关检查 end
        // TODO: PP

        return null;
    }

    @Data
    static class DTO_huoQuJiuZhenRenWenZhenLB {
        UUID jianKangDangAnId;
    }
    // query end
}
