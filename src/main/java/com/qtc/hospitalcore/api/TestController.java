package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.ExtJianChaChanPinCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.chanpin.ChanPin_ChuangJianCmd;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.exception.PPException;
import com.qtc.hospitalcore.domain.util.BiZhong;
import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.*;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ChuangJianJiBenXinXiCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewExtYongHu;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewRepository;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_ShanChuCmd;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.resource.HttpResource;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    PPCommandGateway ppCommandGateway;

    @Autowired
    ZhangHaoViewRepository repository;

    @Autowired
    WenZhenViewRepository wenZhenViewRepository;

    @Autowired
    private EntityManager em;

    static UUID zhangHaoId = UUID.randomUUID();
    static UUID yongHuId = UUID.randomUUID();

    private static final String SESSION_KEY = "PP_KEY";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/t1")
    public String test1(HttpServletRequest request, HttpServletResponse response) {
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, "abc");
        return "ok";
    }

    @GetMapping("/t2")
    public String test2(HttpServletRequest request, HttpServletResponse response) {
        String result = String.valueOf(sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY));

        return result;
    }

    @GetMapping("/t4/{id}")
    public PPResult<ZhangHaoView> test4(@PathVariable UUID id) {
        ZhangHaoView record = repository.findById(id).get();

        return PPResult.getPPResultOK(record);
    }

    @GetMapping("/t5")
    public PPResult<UUID> test5() {
        UUID id = UUID.randomUUID();

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_ChuangJianCmd(
                                id,
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                new BigDecimal("1"),
                                new BigDecimal(("100")),
                                "cpm",
                                "a",
                                "b",
                                PPUtil.stringToMap("A:1, B:1")
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPResultOK(id);
    }

    @GetMapping("/t8/{id}")
    public PPResult<WenZhenView> test8(@PathVariable UUID id) {
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_ZhiFuYuFuKuanCmd(
                                id,
                                "l1",
                                "b1",
                                new BigDecimal("1")
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_ZhiFuBuChongKuanCmd(
                                id,
                                "ll1",
                                OffsetDateTime.now(),
                                "fkf1",
                                BiZhong.REN_MIN_BI,
                                new BigDecimal("98"),
                                1,
                                "b1",
                                Arrays.asList("p1", "p2")
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_ZhiXingTuiKuanCmd(
                                id,
                                "l2",
                                OffsetDateTime.now(),
                                "zhm",
                                "zh",
                                new BigDecimal("1"),
                                "b1",
                                Arrays.asList("pz", "pz2")

                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_ZhiFuBuChongKuanCmd(
                                id,
                                "ll2",
                                OffsetDateTime.now(),
                                "fkf1",
                                BiZhong.REN_MIN_BI,
                                new BigDecimal("3"),
                                1,
                                "b1",
                                Arrays.asList("p1", "p2")
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_AnPaiYiShengCmd(
                                id,
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                UUID.randomUUID()
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_AnPaiHuiZhenCmd(
                                id,
                                OffsetDateTime.now(),
                                "lj1",
                                "hy1",
                                "hf1",
                                "b2"

                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_SheZhiHuiZhenShiPinCmd(
                                id,
                                "splj1"

                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new WenZhen_SheZhiHuiZhenShiPinCmd(
                                id,
                                "splj1"

                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );

        return PPResult.getPPResultOK(id);
    }

    @GetMapping("/t9/{id}")
    public PPResult<WenZhenView> test9(@PathVariable UUID id) {

        WenZhenView result = wenZhenViewRepository.findById(id).get();

        return PPResult.getPPResultOK(result);
    }

    @GetMapping("/t11/{id}")
    public PPResult test11(@PathVariable UUID id) {

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(
                        new ExtJianChaChanPinCmd(
                                id
                        )
                ).withMetaData(PPUtil.stringToMap(""))
        );


        return PPResult.getPPOK();
    }

    @GetMapping("/tt")
    public PPResult init() {
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
                        "   y.deleted = false"
        );

        return PPResult.getPPResultOK(q.getResultList());
    }


}
