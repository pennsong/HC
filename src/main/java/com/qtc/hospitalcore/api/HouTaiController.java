package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.query.ChangPinView;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/houTai")
@Api(tags="后台操作", description = "后台通用操作")
public class HouTaiController {

    // query
    @ApiOperation(value = "获取产品列表")
    @GetMapping("/huoQuChanPinLB")
    public PPResult<List<ChangPinView>> huoQuChanPinLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
        // TODO: PP

        return null;
    }

    @ApiOperation(value = "获取医护人员列表")
    @GetMapping("/huoQuYiHuRenYuanLB")
    public PPResult<List<YiHuRenYuanView>> huoQuYiHuRenYuanLB(@RequestParam(defaultValue="") String queryKey, YiHuRenYuan.QuanXian quanXian, Pageable pageable) {
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
    // query end
}
