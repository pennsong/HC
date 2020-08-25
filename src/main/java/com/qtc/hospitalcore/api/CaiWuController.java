package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.util.BiZhong;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/caiWu")
@Api(tags="财务操作")
public class CaiWuController {

//    // query
//    @ApiOperation(value = "获取问诊付款信息列表")
//    @GetMapping("/huoQuWenZhenFuKuanXinXiLB")
//    public PPResult<List<WenZhenFuKuanXinXiView>> huoQuWenZhenFuKuanXinXiLB(@RequestParam(defaultValue="") String queryKey, Pageable pageable) {
//        // TODO: PP
//
//        return null;
//    }
//
//    @ApiOperation(value = "获取问诊付款信息")
//    @GetMapping("/huoQuWenZhenFuKuanXinXi/{wenZhenId}")
//    public PPResult<WenZhenFuKuanXinXiView> huoQuWenZhenFuKuanXinXi(@PathVariable UUID wenZhenId) {
//        // TODO: PP
//
//        return null;
//    }
//    // query end

    // command

    // command end
}
