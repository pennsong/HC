package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.paiban.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PaiBanTest {
    private FixtureConfiguration<PaiBan> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(PaiBan.class);
    }

    @Test
    public void test_PaiBan_ChuangJianCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        UUID chanPinId = UUID.randomUUID();

        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");

        String yiSheng = "y";

        OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenNoPriorActivity()
                .when(new PaiBan_ChuangJianCmd(
                        id,
                        chanPinId,
                        yuFuFei,
                        shiChangJia,
                        yiSheng,
                        shiJian,
                        xinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PaiBan_ChuangJianEvt(
                        id,
                        chanPinId,
                        yuFuFei,
                        shiChangJia,
                        yiSheng,
                        shiJian,
                        xinXiMap
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinId, state.getChanPinId());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(yiSheng, state.getYiSheng());
                    assertEquals(shiJian, state.getShiJian());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(false, state.isShouChu());
                    assertEquals(PaiBan.ZhuangTai.TING_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_PaiBan_ShangJiaCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        UUID chanPinId = UUID.randomUUID();

        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");

        String yiSheng = "y";

        OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            PaiBan record = new PaiBan();
            record.setId(id);
            record.setChanPinId(chanPinId);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setYiSheng(yiSheng);
            record.setShiJian(shiJian);
            record.setXinXiMap(xinXiMap);

            record.setShouChu(false);
            record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_ShangJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PaiBan_ShangJiaEvt(
                        id
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinId, state.getChanPinId());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(yiSheng, state.getYiSheng());
                    assertEquals(shiJian, state.getShiJian());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(false, state.isShouChu());
                    assertEquals(PaiBan.ZhuangTai.ZAI_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_PaiBan_ShangJiaCmd_失败() {
        // mock data
        UUID id = UUID.randomUUID();

        UUID chanPinId = UUID.randomUUID();

        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");

        String yiSheng = "y";

        OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            PaiBan record = new PaiBan();
            record.setId(id);
            record.setChanPinId(chanPinId);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setYiSheng(yiSheng);
            record.setShiJian(shiJian);
            record.setXinXiMap(xinXiMap);

            record.setShouChu(false);
            record.setZhuangTai(PaiBan.ZhuangTai.ZAI_SHOU);

            return record;
        })
                .when(new PaiBan_ShangJiaCmd(
                        id
                ))
                .expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_PaiBan_XiaJiaCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        UUID chanPinId = UUID.randomUUID();

        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");

        String yiSheng = "y";

        OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            PaiBan record = new PaiBan();
            record.setId(id);
            record.setChanPinId(chanPinId);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setYiSheng(yiSheng);
            record.setShiJian(shiJian);
            record.setXinXiMap(xinXiMap);

            record.setShouChu(false);
            record.setZhuangTai(PaiBan.ZhuangTai.ZAI_SHOU);

            return record;
        })
                .when(new PaiBan_XiaJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PaiBan_XiaJiaEvt(
                        id
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinId, state.getChanPinId());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(yiSheng, state.getYiSheng());
                    assertEquals(shiJian, state.getShiJian());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(false, state.isShouChu());
                    assertEquals(PaiBan.ZhuangTai.TING_SHOU, state.getZhuangTai());
                });
    }

    @Test
    public void test_PaiBan_XiaJiaCmd_失败() {
        // mock data
        UUID id = UUID.randomUUID();

        UUID chanPinId = UUID.randomUUID();

        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");

        String yiSheng = "y";

        OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            PaiBan record = new PaiBan();
            record.setId(id);
            record.setChanPinId(chanPinId);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setYiSheng(yiSheng);
            record.setShiJian(shiJian);
            record.setXinXiMap(xinXiMap);

            record.setShouChu(false);
            record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_XiaJiaCmd(
                        id
                ))
                .expectExceptionMessage("不在在售状态, 不能下架");
    }

    @Test
    public void test_PaiBan_ShanChuCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        UUID chanPinId = UUID.randomUUID();

        BigDecimal yuFuFei = new BigDecimal("1.1");
        BigDecimal shiChangJia = new BigDecimal("10.1");

        String yiSheng = "y";

        OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        // mock data end

        fixture.givenState(() -> {
            PaiBan record = new PaiBan();
            record.setId(id);
            record.setChanPinId(chanPinId);
            record.setYuFuFei(yuFuFei);
            record.setShiChangJia(shiChangJia);
            record.setYiSheng(yiSheng);
            record.setShiJian(shiJian);
            record.setXinXiMap(xinXiMap);

            record.setShouChu(false);
            record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PaiBan_ShanChuEvt(
                        id
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(chanPinId, state.getChanPinId());
                    assertEquals(yuFuFei, state.getYuFuFei());
                    assertEquals(shiChangJia, state.getShiChangJia());
                    assertEquals(yiSheng, state.getYiSheng());
                    assertEquals(shiJian, state.getShiJian());
                    assertEquals(xinXiMap, state.getXinXiMap());

                    assertEquals(false, state.isShouChu());
                    assertEquals(PaiBan.ZhuangTai.TING_SHOU, state.getZhuangTai());

                    assertEquals(true, state.isDeleted());
                });
    }

}
