package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.BiZhong;
import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WenZhen_ZhuanZhenCmdTest {
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

    UUID wenZhenZhangHaoId2 = UUID.randomUUID();
    UUID bingLiBianJiZhangHaoId2 = UUID.randomUUID();
    UUID kaiJuChuFangZhangHaoId2 = UUID.randomUUID();
    UUID queRenChuFangZhangHaoId2 = UUID.randomUUID();

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
    public void test_WenZhen_ZhuanZhenCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhuanZhenCmd(
                        id,
                        wenZhenZhangHaoId2,
                        bingLiBianJiZhangHaoId2,
                        kaiJuChuFangZhangHaoId2,
                        queRenChuFangZhangHaoId2
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setWenZhenZhangHaoId(wenZhenZhangHaoId2);
                    record.setBingLiBianJiZhangHaoId(bingLiBianJiZhangHaoId2);
                    record.setKaiJuChuFangZhangHaoId(kaiJuChuFangZhangHaoId2);
                    record.setQueRenChuFangZhangHaoId(queRenChuFangZhangHaoId2);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        WenZhen_ZhuanZhenEvt evt = new WenZhen_ZhuanZhenEvt(
                id,
                wenZhenZhangHaoId2,
                bingLiBianJiZhangHaoId2,
                kaiJuChuFangZhangHaoId2,
                queRenChuFangZhangHaoId2

        );

        WenZhenView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        WenZhenView record2 = getViewTemplate();
        record2.setWenZhenZhangHaoId(wenZhenZhangHaoId2);
        record2.setBingLiBianJiZhangHaoId(bingLiBianJiZhangHaoId2);
        record2.setKaiJuChuFangZhangHaoId(kaiJuChuFangZhangHaoId2);
        record2.setQueRenChuFangZhangHaoId(queRenChuFangZhangHaoId2);
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
    public void test_WenZhen_ZhuanZhenCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhuanZhenCmd(
                        id,
                        wenZhenZhangHaoId2,
                        bingLiBianJiZhangHaoId2,
                        kaiJuChuFangZhangHaoId2,
                        queRenChuFangZhangHaoId2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能转诊");
    }

}
