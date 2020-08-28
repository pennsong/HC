package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.chanpin.ChanPinViewRepository;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnViewRepository;
import com.qtc.hospitalcore.domain.paiban.PaiBanView;
import com.qtc.hospitalcore.domain.paiban.PaiBanViewRepository;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenViewRepository;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yaopin.YaoPinViewRepository;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewExtYiHuRenYuan;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewExtYongHu;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @Autowired
    private WenZhenViewRepository wenZhenViewRepository;

    @Autowired
    private YaoPinViewRepository yaoPinViewRepository;

    @Autowired
    private ChanPinViewRepository chanPinViewRepository;

    @Autowired
    private PaiBanViewRepository paiBanViewRepository;

    @Autowired
    private JianKangDangAnViewRepository jianKangDangAnViewRepository;


    // query
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/huoQuYongHuLB")
    public PPResult<Page<ZhangHaoViewExtYongHu>> huoQuYongHuLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {

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
                        "   (" +
                        "       y.xing_ming LIKE :keyword " +
                        "   OR" +
                        "       y.xin_xi_map LIKE :keyword" +
                        "   )",
                ZhangHaoViewExtYongHu.class
        );
        q.setParameter("keyword", "%" + queryKey + "%");

        q
                .setFirstResult(new Long(pageable.getOffset()).intValue())
                .setMaxResults(pageable.getPageSize());

        Query q2 = em.createNativeQuery(
                "" +
                        "SELECT " +
                        "   count(*)" +
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
                        "   (" +
                        "       y.xing_ming LIKE :keyword " +
                        "   OR" +
                        "       y.xin_xi_map LIKE :keyword" +
                        "   )"
        );
        q2.setParameter("keyword", "%" + queryKey + "%");

        return PPResult.getPPResultOK(new PageImpl<>(
                q.getResultList(),
                pageable,
                Integer.valueOf(q2.getResultList().get(0).toString())
        ));
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/huoQuYongHu/{zhangHaoId}")
    public PPResult<ZhangHaoViewExtYongHu> huoQuYongHu(@PathVariable UUID zhangHaoId) {
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
                        "   z.id = '" + zhangHaoId + "'",
                ZhangHaoViewExtYongHu.class
        );

        ZhangHaoViewExtYongHu result = (ZhangHaoViewExtYongHu) q.getSingleResult();

        return PPResult.getPPResultOK(result);
    }

    @ApiOperation(value = "获取医护人员列表")
    @GetMapping("/huoQuYiHuRenYuanLB")
    public PPResult<Page<ZhangHaoViewExtYiHuRenYuan>> huoQuYiHuRenYuanLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        Query q = em.createNativeQuery(
                "" +
                        "SELECT " +
                        "   z.id zhang_hao_id," +
                        "   y.id yi_hu_ren_yuan_id," +
                        "   y.xing_ming," +
                        "   y.shen_fen_zheng," +
                        "   y.quan_xian_map," +
                        "   y.xin_xi_map " +
                        "FROM " +
                        "   zhang_hao_view z " +
                        "JOIN " +
                        "   yi_hu_ren_yuan_view y " +
                        "ON " +
                        "   z.yi_hu_ren_yuan_id = y.id " +
                        "WHERE" +
                        "   z.deleted = false " +
                        "AND" +
                        "   y.deleted = false " +
                        "AND " +
                        "   (" +
                        "       y.xing_ming LIKE :keyword " +
                        "   OR" +
                        "       y.xin_xi_map LIKE :keyword" +
                        "   )",
                ZhangHaoViewExtYiHuRenYuan.class
        );
        q.setParameter("keyword", "%" + queryKey + "%");

        q
                .setFirstResult(new Long(pageable.getOffset()).intValue())
                .setMaxResults(pageable.getPageSize());

        Query q2 = em.createNativeQuery(
                "" +
                        "SELECT " +
                        "   count(*)" +
                        "FROM " +
                        "   zhang_hao_view z " +
                        "JOIN " +
                        "   yong_hu_view y " +
                        "ON " +
                        "   z.yi_hu_ren_yuan_id = y.id " +
                        "WHERE" +
                        "   z.deleted = false " +
                        "AND" +
                        "   y.deleted = false " +
                        "AND " +
                        "   (" +
                        "       y.xing_ming LIKE :keyword " +
                        "   OR" +
                        "       y.xin_xi_map LIKE :keyword" +
                        "   )"
        );
        q2.setParameter("keyword", "%" + queryKey + "%");

        return PPResult.getPPResultOK(new PageImpl<>(
                q.getResultList(),
                pageable,
                Integer.valueOf(q2.getResultList().get(0).toString())
        ));
    }

    @ApiOperation(value = "获取医护人员")
    @GetMapping("/huoQuYiHuRenYuan/{zhangHaoId}")
    public PPResult<ZhangHaoViewExtYiHuRenYuan> yiHuRenYuan(@PathVariable UUID zhangHaoId) {
        Query q = em.createNativeQuery(
                "" +
                        "SELECT " +
                        "   z.id zhang_hao_id," +
                        "   y.id yi_hu_ren_yuan_id," +
                        "   y.xing_ming," +
                        "   y.shen_fen_zheng," +
                        "   y.quan_xian_map," +
                        "   y.xin_xi_map " +
                        "FROM " +
                        "   zhang_hao_view z " +
                        "JOIN " +
                        "   yi_hu_ren_yuan_view y " +
                        "ON " +
                        "   z.yi_hu_ren_yuan_id = y.id " +
                        "WHERE" +
                        "   z.deleted = false " +
                        "AND" +
                        "   y.deleted = false " +
                        "AND z.id = '" + zhangHaoId + "'",
                ZhangHaoViewExtYiHuRenYuan.class
        );

        ZhangHaoViewExtYiHuRenYuan result = (ZhangHaoViewExtYiHuRenYuan) q.getSingleResult();

        return PPResult.getPPResultOK(result);
    }

    @ApiOperation(value = "获取问诊列表")
    @GetMapping("/huoQuWenZhenLB")
    public PPResult<Page<WenZhenView>> huoQuWenZhenLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        return PPResult.getPPResultOK(wenZhenViewRepository.findAllByKeyword(queryKey, pageable));
    }

    @ApiOperation(value = "获取问诊")
    @GetMapping("/huoQuWenZhen/{wenZhenId}")
    public PPResult<WenZhenView> wenZhen(@PathVariable UUID wenZhenId) {
        return PPResult.getPPResultOK(wenZhenViewRepository.findById(wenZhenId));
    }

    @ApiOperation(value = "获取药品列表")
    @GetMapping("/huoQuYaoPinLB")
    public PPResult<Page<YaoPinView>> huoQuYaoPinLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        return PPResult.getPPResultOK(yaoPinViewRepository.findAllByKeyword(queryKey, pageable));
    }

    @ApiOperation(value = "获取药品")
    @GetMapping("/huoQuYaoPin/{yaoPinId}")
    public PPResult<YaoPinView> huoQuYaoPinXinXi(@PathVariable UUID yaoPinId) {
        return PPResult.getPPResultOK(yaoPinViewRepository.findById(yaoPinId));
    }

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/huoQuChanPinLB")
    public PPResult<Page<ChanPinView>> huoQuChanPinLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        return PPResult.getPPResultOK(chanPinViewRepository.findAllByKeyword(queryKey, pageable));
    }

    @ApiOperation(value = "获取产品")
    @GetMapping("/huoQuChanPin/{chanPinId}")
    public PPResult<ChanPinView> huoQuChanPin(@PathVariable UUID chanPinId) {
        return PPResult.getPPResultOK(chanPinViewRepository.findById(chanPinId));
    }

    @ApiOperation(value = "获取排班列表")
    @GetMapping("/huoQuPaiBanLB")
    public PPResult<Page<PaiBanView>> huoQuPaiBanLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        return PPResult.getPPResultOK(paiBanViewRepository.findAllByKeyword(queryKey, pageable));
    }

    @ApiOperation(value = "获取排班")
    @GetMapping("/huoQuPaiBan/{paiBanId}")
    public PPResult<PaiBanView> huoQuPaiBan(@PathVariable UUID paiBanId) {
        return PPResult.getPPResultOK(paiBanViewRepository.findById(paiBanId));
    }

    @ApiOperation(value = "获取健康档案列表")
    @GetMapping("/huoQuJianKangDangAnLB")
    public PPResult<Page<JianKangDangAnView>> huoQuJianKangDangAnLB(@RequestParam(defaultValue = "") String queryKey, Pageable pageable) {
        return PPResult.getPPResultOK(jianKangDangAnViewRepository.findAllByKeyword(queryKey, pageable));
    }

    @ApiOperation(value = "获取健康档案问诊列表")
    @PostMapping("/huoQuJianKangDangAnWenZhenLB")
    public PPResult<List<WenZhenView>> huoQuJianKangDangAnWenZhenLB(@Valid @RequestBody DTO_huoQuJiuZhenRenWenZhenLB dto) {
        // 参数相关检查
        // 参数相关检查 end

        return PPResult.getPPResultOK(wenZhenViewRepository.findAllByJianKangDangAnId(dto.getJianKangDangAnId()));
    }

    @Data
    static class DTO_huoQuJiuZhenRenWenZhenLB {
        @NotNull
        UUID jianKangDangAnId;
    }
    // query end
}
