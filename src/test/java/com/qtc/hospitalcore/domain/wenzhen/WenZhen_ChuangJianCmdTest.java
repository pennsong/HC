package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WenZhen_ChuangJianCmdTest {
    private FixtureConfiguration<WenZhen> fixture;

    private WenZhenViewRepository repository = mock(WenZhenViewRepository.class);

    private WenZhenEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();

    UUID chanPinId = UUID.randomUUID();
    UUID paiBanId = UUID.randomUUID();

    UUID jianKangDangAnId = UUID.randomUUID();

    UUID wenZhenZhangHaoId = UUID.randomUUID();
    UUID bingLiBianJiZhangHaoId = UUID.randomUUID();
    UUID kaiJuChuFangZhangHaoId = UUID.randomUUID();
    UUID queRenChuFangZhangHaoId = UUID.randomUUID();

    UUID wenZhenBaoGaoId = UUID.randomUUID();
    UUID wenZhenBaoGaoId2 = UUID.randomUUID();

    UUID zhenLiaoBaoGaoId = UUID.randomUUID();
    UUID zhenLiaoBaoGaoId2 = UUID.randomUUID();
    // mock data end

    private WenZhen getTemplate() {
        WenZhen record = new WenZhen();

        record.setId(id);
        record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);
        record.setXiaDanShiJian(mockNow);
        record.setYuFuFei(new BigDecimal("1"));
        record.setZongJia(new BigDecimal("100"));
        record.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));
        record.setChanPinId(chanPinId);
        record.setChanPinMingCheng("cpm");
        record.setChanPinJsonString("chanPinJsonString");
        record.setPaiBanId(paiBanId);
        record.setPaiBanJsonString("paiBanJsonString");

        // 付费状态
        record.setFuFeiZhuangTai(FuFeiZhuangTai.WEI_FU_FEI);

        // 保险

        // 健康档案
        record.setJianKangDangAnId(jianKangDangAnId);
        record.setJianKangDangAnMap(PPUtil.stringToMap("A:1, B:1"));

        // 医生

        // 资料

        // 款

        return record;
    }

    private WenZhenView getViewTemplate() {
        WenZhenView record = new WenZhenView();

        record.setId(id);
        record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);
        record.setXiaDanShiJian(mockNow);
        record.setYuFuFei(new BigDecimal("1"));
        record.setZongJia(new BigDecimal("100"));
        record.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));
        record.setChanPinId(chanPinId);
        record.setChanPinMingCheng("cpm");
        record.setChanPinJsonString("chanPinJsonString");
        record.setPaiBanId(paiBanId);
        record.setPaiBanJsonString("paiBanJsonString");

        // 付费状态
        record.setFuFeiZhuangTai(FuFeiZhuangTai.WEI_FU_FEI);

        // 保险

        // 健康档案
        record.setJianKangDangAnId(jianKangDangAnId);
        record.setJianKangDangAnMap(PPUtil.stringToMap("A:1, B:1"));

        // 医生

        // 资料

        // 款

        return record;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(WenZhen.class);

        eventListener = new WenZhenEventListener(repository);
    }

    @Test
    public void test_WenZhen_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new WenZhen_ChuangJianCmd(
                        id,
                        jianKangDangAnId,
                        PPUtil.stringToMap("A:1, B:1"),
                        chanPinId,
                        paiBanId,
                        new BigDecimal("1"),
                        new BigDecimal("100"),
                        "cpm",
                        "chanPinJsonString",
                        "paiBanJsonString",
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    // 时间相关assertions
                    assertTrue(Duration.between(state.getXiaDanShiJian(), OffsetDateTime.now()).getSeconds() < 1);
                    // 时间相关assertions end

                    record.setXiaDanShiJian(nullTime);
                    state.setXiaDanShiJian(nullTime);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_ChuangJianEvt evt = new WenZhen_ChuangJianEvt(
                id,
                jianKangDangAnId,
                PPUtil.stringToMap("A:1, B:1"),
                chanPinId,
                paiBanId,
                new BigDecimal("1"),
                new BigDecimal("100"),
                "cpm",
                "chanPinJsonString",
                "paiBanJsonString",
                PPUtil.stringToMap("A:1, B:1"),
                mockNow
        );
        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<WenZhenView> captor = ArgumentCaptor.forClass(WenZhenView.class);
        verify(repository).saveAndFlush(captor.capture());
        WenZhenView state = captor.getValue();

        // 时间相关assertions
        assertTrue(Duration.between(state.getXiaDanShiJian(), OffsetDateTime.now()).getSeconds() < 1);
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        record2.setXiaDanShiJian(nullTime);
        state.setXiaDanShiJian(nullTime);
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }
}
