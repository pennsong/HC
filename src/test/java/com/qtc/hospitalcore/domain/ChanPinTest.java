package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.zhanghao.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChanPinTest {
    private FixtureConfiguration<ChanPin> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ChanPin.class);
    }

    @Test
    public void test_ChanPin_ChuangJianCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenNoPriorActivity()
                .when(new ChanPin_ChuangJianCmd(
                        id,
                        chanPinMing,
                        daLeiXing,
                        xiaoLeiXing,
                        yuFuFei,
                        shiChangJia,
                        xinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_ChuangJianEvt(
                        id,
                        chanPinMing,
                        daLeiXing,
                        xiaoLeiXing,
                        yuFuFei,
                        shiChangJia,
                        xinXiMap
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinMing, state.getChanPinMing());
                    assertEquals(daLeiXing, state.getDaLeiXing());
                    assertEquals(xiaoLeiXing, state.getXiaoLeiXing());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(ChanPin.ZhuangTai.TING_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_ChanPin_GengXinCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        String chanPinMing2 = "c2";
        String daLeiXing2 = "d2";
        String xiaoLeiXing2 = "x2";
        BigDecimal yuFuFei2 = new BigDecimal("2.2");
        BigDecimal shiChangJia2 = new BigDecimal("20.2");
        Map<String, Object> xinXiMap2 = PPUtil.stringToMap("A:2, B:2");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = new ChanPin();
            record.setId(id);
            record.setChanPinMing(chanPinMing);
            record.setDaLeiXing(daLeiXing);
            record.setXiaoLeiXing(xiaoLeiXing);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setXinXiMap(xinXiMap);

            record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new ChanPin_GengXinCmd(
                        id,
                        chanPinMing2,
                        daLeiXing2,
                        xiaoLeiXing2,
                        yuFuFei2,
                        shiChangJia2,
                        xinXiMap2

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_GengXinEvt(
                        id,
                        chanPinMing2,
                        daLeiXing2,
                        xiaoLeiXing2,
                        yuFuFei2,
                        shiChangJia2,
                        xinXiMap2
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinMing2, state.getChanPinMing());
                    assertEquals(daLeiXing2, state.getDaLeiXing());
                    assertEquals(xiaoLeiXing2, state.getXiaoLeiXing());
                    assertEquals(yuFuFei2, state.getYuFuFei());
                    assertEquals(shiChangJia2, state.getShiChangJia());
                    assertEquals(xinXiMap2, state.getXinXiMap());

                    assertEquals(ChanPin.ZhuangTai.TING_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_ChanPin_ShangJiaCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = new ChanPin();
            record.setId(id);
            record.setChanPinMing(chanPinMing);
            record.setDaLeiXing(daLeiXing);
            record.setXiaoLeiXing(xiaoLeiXing);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setXinXiMap(xinXiMap);

            record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new ChanPin_ShangJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_ShangJiaEvt(
                        id
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinMing, state.getChanPinMing());
                    assertEquals(daLeiXing, state.getDaLeiXing());
                    assertEquals(xiaoLeiXing, state.getXiaoLeiXing());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(ChanPin.ZhuangTai.ZAI_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_ChanPin_ShangJiaCmd_失败() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = new ChanPin();
            record.setId(id);
            record.setChanPinMing(chanPinMing);
            record.setDaLeiXing(daLeiXing);
            record.setXiaoLeiXing(xiaoLeiXing);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setXinXiMap(xinXiMap);

            record.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);

            return record;
        })
                .when(new ChanPin_ShangJiaCmd(
                        id
                ))
                .expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_ChanPin_XiaJiaCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = new ChanPin();
            record.setId(id);
            record.setChanPinMing(chanPinMing);
            record.setDaLeiXing(daLeiXing);
            record.setXiaoLeiXing(xiaoLeiXing);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setXinXiMap(xinXiMap);

            record.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);

            return record;
        })
                .when(new ChanPin_XiaJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_XiaJiaEvt(
                        id
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinMing, state.getChanPinMing());
                    assertEquals(daLeiXing, state.getDaLeiXing());
                    assertEquals(xiaoLeiXing, state.getXiaoLeiXing());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(ChanPin.ZhuangTai.TING_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_ChanPin_XiaJiaCmd_失败() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = new ChanPin();
            record.setId(id);
            record.setChanPinMing(chanPinMing);
            record.setDaLeiXing(daLeiXing);
            record.setXiaoLeiXing(xiaoLeiXing);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setXinXiMap(xinXiMap);

            record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new ChanPin_XiaJiaCmd(
                        id
                ))
                .expectExceptionMessage("不在在售状态, 不能下架");
    }

    @Test
    public void test_ChanPin_ShanChuCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String chanPinMing = "c";
        String daLeiXing = "d";
        String xiaoLeiXing = "x";
        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = new ChanPin();
            record.setId(id);
            record.setChanPinMing(chanPinMing);
            record.setDaLeiXing(daLeiXing);
            record.setXiaoLeiXing(xiaoLeiXing);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setXinXiMap(xinXiMap);

            record.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);

            return record;
        })
                .when(new ChanPin_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_ShanChuEvt(
                        id
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinMing, state.getChanPinMing());
                    assertEquals(daLeiXing, state.getDaLeiXing());
                    assertEquals(xiaoLeiXing, state.getXiaoLeiXing());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(true, state.isDeleted());
                });
    }

}
