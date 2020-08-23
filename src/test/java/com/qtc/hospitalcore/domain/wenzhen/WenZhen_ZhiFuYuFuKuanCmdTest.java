package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WenZhen_ZhiFuYuFuKuanCmdTest {
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

        // 会诊

        // 处方

        // 报告

        return record;
    }

    private WenZhenView getViewTemplate() {
        WenZhenView record = new WenZhenView();

        record.setId(id);
        record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);
        record.setXiaDanShiJian(mockNow);
        record.setYuFuFei(new BigDecimal("1"));
        record.setZongJia(new BigDecimal("100"));
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

        // 会诊

        // 处方

        // 报告

        return record;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(WenZhen.class);

        eventListener = new WenZhenEventListener(repository);
    }

    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        "yfls",
                        "bz",
                        new BigDecimal(10)
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);
                    record.setYuFuKuan(
                            new WenZhen.YuFuKuan(
                                    "yfls",
                                    mockNow,
                                    new BigDecimal(10),
                                    "bz"
                            )
                    );

                    // 时间相关assertions
                    assertTrue(Duration.between(state.getYuFuKuan().getShiJian(), OffsetDateTime.now()).getSeconds() < 1);
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    record.getYuFuKuan().setShiJian(nullTime);
                    state.getYuFuKuan().setShiJian(nullTime);
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_ZhiFuYuFuKuanEvt evt = new WenZhen_ZhiFuYuFuKuanEvt(
                id,
                "yfls",
                "bz",
                new BigDecimal(10),
                mockNow
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);
        record2.setYuFuKuan(
                new YuFuKuan(
                        "yfls",
                        mockNow,
                        new BigDecimal(10),
                        "bz"
                )
        );
        // 修改record到预期结果 end

        ArgumentCaptor<WenZhenView> captor = ArgumentCaptor.forClass(WenZhenView.class);
        verify(repository).saveAndFlush(captor.capture());
        WenZhenView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        "yfls",
                        "bz",
                        new BigDecimal(10)
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已创建状态才能接收预付款");
    }

    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        "yfls",
                        "bz",
                        new BigDecimal(10)
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在没有付费时才能接收预付款");
    }

    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        "yfls",
                        "bz",
                        new BigDecimal(0.9)
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("预付款不足");
    }

    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_4() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        "yfls",
                        "bz",
                        new BigDecimal(100.1)
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("预付款不能超过总价");
    }
}
