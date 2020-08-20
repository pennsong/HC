package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.paiban.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaiBanTest {
    FixtureConfiguration<PaiBan> fixture;

    // mock data
    UUID id = UUID.randomUUID();

    PaiBan.ZhuangTai zhuangTai = PaiBan.ZhuangTai.ZAI_SHOU;

    UUID chanPinId = UUID.randomUUID();

    BigDecimal yuFuFei = new BigDecimal("1.1");
    BigDecimal shiChangJia = new BigDecimal("10.1");

    String yiSheng = "y";

    OffsetDateTime shiJian = OffsetDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);

    boolean shouChu = false;

    Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

    // mock data end

    private PaiBan getTemplate() {
        PaiBan template = new PaiBan();
        template.setId(id);
        template.setZhuangTai(zhuangTai);
        template.setChanPinId(chanPinId);
        template.setYuFuFei(yuFuFei);
        template.setShiChangJia(shiChangJia);
        template.setYiSheng(yiSheng);
        template.setShiJian(shiJian);
        template.setShouChu(shouChu);
        template.setXinXiMap(xinXiMap);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(PaiBan.class);
    }

    @Test
    public void test_PaiBan_ChuangJianCmd() {

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
                    PaiBan record = getTemplate();
                    record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_PaiBan_ShangJiaCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
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
                    PaiBan record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_PaiBan_ShangJiaCmd_失败() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

            return record;
        })
                .when(new PaiBan_ShangJiaCmd(
                        id
                ))
                   .expectException(PPBusinessException.class)
.expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_PaiBan_XiaJiaCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

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
                    PaiBan record = getTemplate();
                    record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_PaiBan_XiaJiaCmd_失败() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_XiaJiaCmd(
                        id
                ))
                   .expectException(PPBusinessException.class)
.expectExceptionMessage("不在在售状态, 不能下架");
    }

    @Test
    public void test_PaiBan_ShouChuCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

            return record;
        })
                .when(new PaiBan_ShouChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new PaiBan_ShouChuEvt(
                        id
                ))
                .expectState(state -> {
                    PaiBan record = getTemplate();
                    record.setShouChu(true);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_PaiBan_ShouChuCmd_失败_1() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_ShouChuCmd(
                        id
                ))
                   .expectException(PPBusinessException.class)
.expectExceptionMessage("非在售状态, 不能售出");
    }

    @Test
    public void test_PaiBan_ShouChuCmd_失败_2() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setShouChu(true);

            return record;
        })
                .when(new PaiBan_ShouChuCmd(
                        id
                ))
                   .expectException(PPBusinessException.class)
.expectExceptionMessage("已售出, 不能再次售出");
    }

    @Test
    public void test_PaiBan_ShanChuCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

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
                    PaiBan record = getTemplate();
                    record.delete();

                    // perform assertions
                    assertEquals(record, state);
                });
    }

}
