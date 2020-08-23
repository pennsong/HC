package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.matchers.EqualFieldsMatcher;
import org.hamcrest.Matchers;
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

import static org.axonframework.test.matchers.Matchers.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WenZhen_ZhiFuBuChongKuanCmdTest {
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
        record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

        // 保险

        // 健康档案
        record.setJianKangDangAnId(jianKangDangAnId);
        record.setJianKangDangAnMap(PPUtil.stringToMap("A:1, B:1"));

        // 医生

        // 资料

        // 款
        record.setYuFuKuan(
                new WenZhen.YuFuKuan(
                        "yfls",
                        mockNow,
                        new BigDecimal(10),
                        "bz"
                )
        );

        record.setBuChongKuanList(
                new LinkedList<>(
                        Arrays.asList(
                                new WenZhen.BuChongKuan(
                                        "bcl",
                                        mockNow,
                                        "fkf",
                                        "bz",
                                        new BigDecimal(10),
                                        2,
                                        "bz",
                                        "pz_1,pz_2"
                                )
                        )
                )
        );

        record.setTuiKuanList(
                new LinkedList<>(
                        Arrays.asList(
                                new WenZhen.TuiKuan(
                                        "tkl",
                                        mockNow,
                                        "skzhm",
                                        "skzh",
                                        new BigDecimal("10"),
                                        "bz",
                                        "pz_1,pz_2"
                                )
                        )
                )
        );

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
        record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

        // 保险

        // 健康档案
        record.setJianKangDangAnId(jianKangDangAnId);
        record.setJianKangDangAnMap(PPUtil.stringToMap("A:1, B:1"));

        // 医生

        // 资料

        // 款
        record.setYuFuKuan(
                new YuFuKuan(
                        "yfls",
                        mockNow,
                        new BigDecimal(10),
                        "bz"
                )
        );

        record.setBuChongKuanList(
                new LinkedList<>(
                        Arrays.asList(
                                new BuChongKuan(
                                        "bcl",
                                        mockNow,
                                        "fkf",
                                        "bz",
                                        new BigDecimal(10),
                                        2,
                                        "bz",
                                        "pz_1,pz_2"
                                )
                        )
                )
        );

        record.setTuiKuanList(
                new LinkedList<>(
                        Arrays.asList(
                                new TuiKuan(
                                        "tkl",
                                        mockNow,
                                        "skzhm",
                                        "skzh",
                                        new BigDecimal("10"),
                                        "bz",
                                        "pz_1,pz_2"
                                )
                        )
                )
        );

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
    public void test_WenZhen_ZhiFuBuChongKuanCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(79),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.getBuChongKuanList().add(
                            new WenZhen.BuChongKuan(
                                    "bcl3",
                                    mockNow,
                                    "fkf3",
                                    "bz3",
                                    new BigDecimal(79),
                                    1,
                                    "bbz3",
                                    "bp3_1,bp3_2"
                            )
                    );

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_ZhiFuBuChongKuanEvt evt = new WenZhen_ZhiFuBuChongKuanEvt(
                id,
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(79),
                1,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.getBuChongKuanList().add(
                new BuChongKuan(
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(79),
                        1,
                        "bbz3",
                        "bp3_1,bp3_2"
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
    public void test_WenZhen_ZhiFuBuChongKuanCmd_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectEventsMatching(
                        payloadsMatching(exactSequenceOf(
                                instanceOf(WenZhen_ZhiFuBuChongKuanEvt.class),
                                instanceOf(WenZhen_ZhiFuQuanKuanEvt.class),
                                andNoMore()))
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
                    record.getBuChongKuanList().add(
                            new WenZhen.BuChongKuan(
                                    "bcl3",
                                    mockNow,
                                    "fkf3",
                                    "bz3",
                                    new BigDecimal(80),
                                    1,
                                    "bbz3",
                                    "bp3_1,bp3_2"
                            )
                    );

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_ZhiFuBuChongKuanEvt evt = new WenZhen_ZhiFuBuChongKuanEvt(
                id,
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(80),
                1,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();

        record2.getBuChongKuanList().add(
                new BuChongKuan(
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        "bp3_1,bp3_2"
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
    public void test_WenZhen_ZhiFuQuanKuanEvt() {
        // query model update
        WenZhen_ZhiFuQuanKuanEvt evt2 = new WenZhen_ZhiFuQuanKuanEvt(
                id
        );

        WenZhenView record3 = getViewTemplate();

        Mockito.when(repository.findById(evt2.getId()))
                .thenReturn(Optional.of(record3));

        eventListener.on(evt2);

        // 修改record到预期结果
        WenZhenView record4 = getViewTemplate();

        record4.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
        // 修改record到预期结果 end

        ArgumentCaptor<WenZhenView> captor2 = ArgumentCaptor.forClass(WenZhenView.class);
        verify(repository).saveAndFlush(captor2.capture());
        WenZhenView state2 = captor2.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record4, state2);
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(ZhuangTai.YI_CHENG_GONG_WAN_CHENG);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在非已完成状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(ZhuangTai.YI_JIE_SHU_WAN_CHENG);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在非已完成状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(FuFeiZhuangTai.WEI_FU_FEI);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付预付费状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_4() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl3",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付预付费状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_5() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        "bcl",
                        mockNow,
                        "fkf3",
                        "bz3",
                        new BigDecimal(80),
                        1,
                        "bbz3",
                        Arrays.asList("bp3_1", "bp3_2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("流水号不能重复");
    }
}
