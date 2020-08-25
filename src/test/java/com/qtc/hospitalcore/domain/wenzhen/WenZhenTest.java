package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.BiZhong;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.*;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoEventListener;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoViewRepository;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class WenZhenTest {
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
        record.setZhuangTai(ZhuangTai.YI_AN_PAI_YI_SHENG);
        record.setXiaDanShiJian(mockNow);
        record.setYuFuFei(new BigDecimal("1"));
        record.setZongJia(new BigDecimal("100"));
        record.setChanPinId(chanPinId);
        record.setChanPinMingCheng("cpm");
        record.setChanPinJsonString("chanPinJsonString");
        record.setPaiBanId(paiBanId);
        record.setPaiBanJsonString("paiBanJsonString");

        // 付费状态
        record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

        // 保险
        record.setBaoXianDanHao("bx");
        record.setXianZhongMingCheng("xz");

        // 健康档案
        record.setJianKangDangAnId(jianKangDangAnId);
        record.setJianKangDangAnMap(PPUtil.stringToMap("A:1, B:1"));

        // 医生
        record.setWenZhenZhangHaoId(wenZhenZhangHaoId);
        record.setBingLiBianJiZhangHaoId(bingLiBianJiZhangHaoId);
        record.setKaiJuChuFangZhangHaoId(kaiJuChuFangZhangHaoId);
        record.setQueRenChuFangZhangHaoId(queRenChuFangZhangHaoId);

        // 资料
        record.setMuQianZhuYaoZhenDuan("mq");
        record.setXiangXiZhiLiaoJingGuoMap(PPUtil.stringToMap("A:1, B:1"));
        record.setJianChaZongJieMap(PPUtil.stringToMap("C:1, D:1"));
        record.setDianZiYingXiangMap(PPUtil.stringToMap("E:1, F:1"));
        record.setQiTaCaiLiaoMap(PPUtil.stringToMap("G:1, H:1"));
        record.setWenZhenZongJieMap(PPUtil.stringToMap("I:1, J:1"));

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
                                        "bcls",
                                        mockNow,
                                        "fkf",
                                        BiZhong.REN_MIN_BI,
                                        new BigDecimal(10),
                                        1,
                                        "bz",
                                        "pz_1,pz_2"
                                ),
                                new WenZhen.BuChongKuan(
                                        "bcls2",
                                        mockNow,
                                        "fkf2",
                                        BiZhong.REN_MIN_BI,
                                        new BigDecimal(20),
                                        2,
                                        "bz2",
                                        "pz2_1,pz2_2"
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
                                ),
                                new WenZhen.TuiKuan(
                                        "tkl2",
                                        mockNow,
                                        "skzhm2",
                                        "skzh2",
                                        new BigDecimal("20"),
                                        "bz2",
                                        "pz2_1,pz2_2"
                                )
                        )
                )
        );

        // 会诊
        record.setHuiZhen(
                new WenZhen.HuiZhen(
                        HuiZhenZhuangTai.YI_AN_PAI,
                        mockNow,
                        "lj",
                        "hy",
                        "hf",
                        "bz",
                        "sp"
                )
        );

        // 处方
        record.setChuFang(
                new WenZhen.ChuFang(
                        ChuFangZhuangTai.YI_QUE_REN,
                        "zw",
                        kaiJuChuFangZhangHaoId,
                        mockNow,
                        queRenChuFangZhangHaoId,
                        mockNow,
                        null
                )
        );

        // 报告
        record.setWenZhenBaoGaoList(
                Arrays.asList(
                        new WenZhen.WenZhenBaoGao(
                                wenZhenBaoGaoId,
                                "zw",
                                wenZhenZhangHaoId,
                                mockNow
                        ),
                        new WenZhen.WenZhenBaoGao(
                                wenZhenBaoGaoId2,
                                "zw2",
                                wenZhenZhangHaoId,
                                mockNow
                        )
                )
        );

        record.setZhenLiaoBaoGaoList(
                Arrays.asList(
                        new WenZhen.ZhenLiaoBaoGao(
                                wenZhenBaoGaoId,
                                "zw",
                                wenZhenZhangHaoId,
                                mockNow
                        ),
                        new WenZhen.ZhenLiaoBaoGao(
                                wenZhenBaoGaoId2,
                                "zw2",
                                wenZhenZhangHaoId,
                                mockNow
                        )
                )
        );


        return record;
    }

    private WenZhenView getViewTemplate() {
        WenZhenView record = new WenZhenView();

        record.setId(id);
        record.setZhuangTai(ZhuangTai.YI_AN_PAI_YI_SHENG);
        record.setXiaDanShiJian(mockNow);
        record.setYuFuFei(new BigDecimal("1"));
        record.setZongJia(new BigDecimal("100"));
        record.setChanPinId(chanPinId);
        record.setChanPinMingCheng("cpm");
        record.setChanPinJsonString("chanPinJsonString");
        record.setPaiBanId(paiBanId);
        record.setPaiBanJsonString("paiBanJsonString");

        // 付费状态
        record.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

        // 保险
        record.setBaoXianDanHao("bx");
        record.setXianZhongMingCheng("xz");

        // 健康档案
        record.setJianKangDangAnId(jianKangDangAnId);
        record.setJianKangDangAnMap(PPUtil.stringToMap("A:1, B:1"));

        // 医生
        record.setWenZhenZhangHaoId(wenZhenZhangHaoId);
        record.setBingLiBianJiZhangHaoId(bingLiBianJiZhangHaoId);
        record.setKaiJuChuFangZhangHaoId(kaiJuChuFangZhangHaoId);
        record.setQueRenChuFangZhangHaoId(queRenChuFangZhangHaoId);

        // 资料
        record.setMuQianZhuYaoZhenDuan("mq");
        record.setXiangXiZhiLiaoJingGuoMap(PPUtil.stringToMap("A:1, B:1"));
        record.setJianChaZongJieMap(PPUtil.stringToMap("C:1, D:1"));
        record.setDianZiYingXiangMap(PPUtil.stringToMap("E:1, F:1"));
        record.setQiTaCaiLiaoMap(PPUtil.stringToMap("G:1, H:1"));
        record.setWenZhenZongJieMap(PPUtil.stringToMap("I:1, J:1"));

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
                                        "bcls",
                                        mockNow,
                                        "fkf",
                                        BiZhong.REN_MIN_BI,
                                        new BigDecimal(10),
                                        1,
                                        "bz",
                                        "pz_1,pz_2"
                                ),
                                new BuChongKuan(
                                        "bcls2",
                                        mockNow,
                                        "fkf2",
                                        BiZhong.REN_MIN_BI,
                                        new BigDecimal(20),
                                        2,
                                        "bz2",
                                        "pz2_1,pz2_2"
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
                                ),
                                new TuiKuan(
                                        "tkl2",
                                        mockNow,
                                        "skzhm2",
                                        "skzh2",
                                        new BigDecimal("20"),
                                        "bz2",
                                        "pz2_1,pz2_2"
                                )
                        )
                )
        );

        // 会诊
        record.setHuiZhen(
                new HuiZhen(
                        HuiZhenZhuangTai.YI_AN_PAI,
                        mockNow,
                        "lj",
                        "hy",
                        "hf",
                        "bz",
                        "sp"
                )
        );

        // 处方
        record.setChuFang(
                new ChuFang(
                        ChuFangZhuangTai.YI_QUE_REN,
                        "zw",
                        kaiJuChuFangZhangHaoId,
                        mockNow,
                        queRenChuFangZhangHaoId,
                        mockNow,
                        null
                )
        );

        // 报告
        record.setWenZhenBaoGaoList(
                Arrays.asList(
                        new WenZhenBaoGao(
                                wenZhenBaoGaoId,
                                "zw",
                                wenZhenZhangHaoId,
                                mockNow
                        ),
                        new WenZhenBaoGao(
                                wenZhenBaoGaoId2,
                                "zw2",
                                wenZhenZhangHaoId,
                                mockNow
                        )
                )
        );

        record.setZhenLiaoBaoGaoList(
                Arrays.asList(
                        new ZhenLiaoBaoGao(
                                wenZhenBaoGaoId,
                                "zw",
                                wenZhenZhangHaoId,
                                mockNow
                        ),
                        new ZhenLiaoBaoGao(
                                wenZhenBaoGaoId2,
                                "zw2",
                                wenZhenZhangHaoId,
                                mockNow
                        )
                )
        );


        return record;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(WenZhen.class);

        eventListener = new WenZhenEventListener(repository);
    }

    @Test
    public void test_WenZhen_GengXinMuQianZhuYaoZhenDuanCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
                        id,
                        "mq2"
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setMuQianZhuYaoZhenDuan("mq2");

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_GengXinMuQianZhuYaoZhenDuanEvt evt = new WenZhen_GengXinMuQianZhuYaoZhenDuanEvt(
                id,
                "mq2"
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();

        record2.setMuQianZhuYaoZhenDuan("mq2");
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
    public void test_WenZhen_GengXinMuQianZhuYaoZhenDuanCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
                        id,
                        "mq2"
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能递交目前主要诊断");
    }

    @Test
    public void test_WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setXiangXiZhiLiaoJingGuoMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt evt = new WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt(
                id,
                PPUtil.stringToMap("B:2, C:2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setXiangXiZhiLiaoJingGuoMap(PPUtil.stringToMap("B:2, C:2"));
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
    public void test_WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新详细治疗经过");
    }

    @Test
    public void test_WenZhen_GengXinJianChaZongJieCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinJianChaZongJieCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setJianChaZongJieMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_GengXinJianChaZongJieEvt evt = new WenZhen_GengXinJianChaZongJieEvt(
                id,
                PPUtil.stringToMap("B:2, C:2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setJianChaZongJieMap(PPUtil.stringToMap("B:2, C:2"));
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
    public void test_WenZhen_GengXinJianChaZongJieCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_GengXinJianChaZongJieCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新检查总结");
    }

    @Test
    public void test_WenZhen_GengXinDianZiYingXiangCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinDianZiYingXiangCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setDianZiYingXiangMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_GengXinDianZiYingXiangEvt evt = new WenZhen_GengXinDianZiYingXiangEvt(
                id,
                PPUtil.stringToMap("B:2, C:2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setDianZiYingXiangMap(PPUtil.stringToMap("B:2, C:2"));
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
    public void test_WenZhen_GengXinDianZiYingXiangCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_GengXinDianZiYingXiangCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新电子影像");
    }

    @Test
    public void test_WenZhen_GengXinQiTaCaiLiaoCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinQiTaCaiLiaoCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setQiTaCaiLiaoMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_GengXinQiTaCaiLiaoEvt evt = new WenZhen_GengXinQiTaCaiLiaoEvt(
                id,
                PPUtil.stringToMap("B:2, C:2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setQiTaCaiLiaoMap(PPUtil.stringToMap("B:2, C:2"));
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
    public void test_WenZhen_GengXinQiTaCaiLiaoCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_GengXinQiTaCaiLiaoCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新其他材料");
    }
    
    @Test
    public void test_WenZhen_GengXinWenZhenZongJieCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinWenZhenZongJieCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setWenZhenZongJieMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_GengXinWenZhenZongJieEvt evt = new WenZhen_GengXinWenZhenZongJieEvt(
                id,
                PPUtil.stringToMap("B:2, C:2")
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setWenZhenZongJieMap(PPUtil.stringToMap("B:2, C:2"));
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
    public void test_WenZhen_GengXinWenZhenZongJieCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_GengXinWenZhenZongJieCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新问诊总结");
    }
    
    @Test
    public void test_WenZhen_ChengGongWanChengCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ChengGongWanChengCmd(
                        id,
                        "bz"
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setZhuangTai(ZhuangTai.YI_CHENG_GONG_WAN_CHENG);
                    record.setWanChengBeiZhu("bz");

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_ChengGongWanChengEvt evt = new WenZhen_ChengGongWanChengEvt(
                id,
                "bz"
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
       
        record2.setZhuangTai(ZhuangTai.YI_CHENG_GONG_WAN_CHENG);
        record2.setWanChengBeiZhu("bz");
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
    public void test_WenZhen_JieShuWanChengCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_JieShuWanChengCmd(
                        id,
                        "bz"
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setZhuangTai(ZhuangTai.YI_JIE_SHU_WAN_CHENG);
                    record.setWanChengBeiZhu("bz");

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_JieShuWanChengEvt evt = new WenZhen_JieShuWanChengEvt(
                id,
                "bz"
        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();

        record2.setZhuangTai(ZhuangTai.YI_JIE_SHU_WAN_CHENG);
        record2.setWanChengBeiZhu("bz");
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
}
