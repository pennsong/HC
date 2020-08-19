package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class YaoPinTest {
    private FixtureConfiguration<ChanPin> fixture;

    // mock data
    UUID id = UUID.randomUUID();

    String mingCheng = "c";
    String daLeiXing = "d";
    String xiaoLeiXing = "x";
    BigDecimal yuFuFei = new BigDecimal("1.1");
    BigDecimal shiChangJia = new BigDecimal("10.1");
    Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

    // mock data end

    private ChanPin getTemplate() {
        ChanPin template = new ChanPin();
        template.setId(id);
        template.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);
        template.setMingCheng(mingCheng);
        template.setDaLeiXing(daLeiXing);
        template.setXiaoLeiXing(xiaoLeiXing);
        template.setYuFuFei(yuFuFei);
        template.setShiChangJia(shiChangJia);
        template.setXinXiMap(xinXiMap);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ChanPin.class);
    }

    @Test
    public void test_ChanPin_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new ChanPin_ChuangJianCmd(
                        id,
                        mingCheng,
                        daLeiXing,
                        xiaoLeiXing,
                        yuFuFei,
                        shiChangJia,
                        xinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_ChuangJianEvt(
                        id,
                        mingCheng,
                        daLeiXing,
                        xiaoLeiXing,
                        yuFuFei,
                        shiChangJia,
                        xinXiMap
                ))
                .expectState(state -> {
                    ChanPin record = getTemplate();
                    record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ChanPin_GengXinCmd() {
        // mock data
        String mingCheng2 = "c2";
        String daLeiXing2 = "d2";
        String xiaoLeiXing2 = "x2";
        BigDecimal yuFuFei2 = new BigDecimal("2.2");
        BigDecimal shiChangJia2 = new BigDecimal("20.1");
        Map<String, Object> xinXiMap2 = PPUtil.stringToMap("B:2, C:2");

        // mock data end

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_GengXinCmd(
                        id,
                        mingCheng2,
                        daLeiXing2,
                        xiaoLeiXing2,
                        yuFuFei2,
                        shiChangJia2,
                        xinXiMap2

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_GengXinEvt(
                        id,
                        mingCheng2,
                        daLeiXing2,
                        xiaoLeiXing2,
                        yuFuFei2,
                        shiChangJia2,
                        xinXiMap2
                ))
                .expectState(state -> {
                    ChanPin record = getTemplate();
                    record.setMingCheng(mingCheng2);
                    record.setDaLeiXing(daLeiXing2);
                    record.setXiaoLeiXing(xiaoLeiXing2);
                    record.setYuFuFei(yuFuFei2);
                    record.setShiChangJia(shiChangJia2);
                    record.setXinXiMap(xinXiMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ChanPin_ShangJiaCmd() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();
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
                    ChanPin record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ChanPin_ShangJiaCmd_失败() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_ShangJiaCmd(
                        id
                ))
                .expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_ChanPin_XiaJiaCmd() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

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
                    ChanPin record = getTemplate();
                    record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ChanPin_XiaJiaCmd_失败() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();
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

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

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
                    ChanPin record = getTemplate();
                    record.delete();

                    // perform assertions
                    assertEquals(record, state);
                });
    }

}
