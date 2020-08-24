package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import com.qtc.hospitalcore.domain.paiban.PaiBanView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/houTai")
@Api(tags="后台操作", description = "后台通用操作")
public class HouTaiController {

    // query
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/huoQuYongHuLB")
    public PPResult<List<YongHuView>> huoQuYongHuLB(@RequestParam(defaultValue="") String queryKey, @PageableDefault(page=0, size=20) Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/huoQuYongHu/{yongHuId}")
    public PPResult<YongHuView> huoQuYongHu(@PathVariable UUID yongHuId) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取医护人员列表")
    @GetMapping("/huoQuYiHuRenYuanLB")
    public PPResult<List<YiHuRenYuanView>> huoQuYiHuRenYuanLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
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
    public PPResult<List<WenZhenView>> huoQuWenZhenLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
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
    public PPResult<List<YaoPinView>> huoQuYaoPinLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
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
    public PPResult<List<ChanPinView>> huoQuChanPinLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
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
    public PPResult<List<PaiBanView>> huoQuPaiBanLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
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
    public PPResult<List<JianKangDangAnView>> huoQuJianKangDangAnLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
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
    static class DTO_huoQuJiuZhenRenWenZhenLB{
        UUID jianKangDangAnId;
    }
    // query end
}
