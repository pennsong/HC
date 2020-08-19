package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WenZhenTest {
    private FixtureConfiguration<WenZhen> fixture;

    // mock data
    // mock now
    LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

    UUID id = UUID.randomUUID();

    UUID jianKangDangAnId = UUID.randomUUID();

    LocalDateTime xiaDanShiJian = mockNow;

    BigDecimal yuFuFei = new BigDecimal("1");
    BigDecimal zongJia = new BigDecimal("100");

    // 产品相关
    UUID chanPinId = UUID.randomUUID();
    String chanPinMingCheng = "cpm";
    String chanPinJsonString = "chanPinJsonString";

    // 排班相关
    UUID paiBanId = UUID.randomUUID();
    String paiBanJsonString = "paiBanJsonString";

    // 保险相关
    String baoXianDanHao = "bx";
    String xianZhongMingCheng = "xz";

    // 健康档案相关
    Map<String, Object> jianKangDangAnMap = PPUtil.stringToMap("A:1, B:1");

    // 医护人员相关
    UUID wenZhenZhangHaoId = UUID.randomUUID();
    UUID bingLiBianJiZhangHaoId = UUID.randomUUID();
    UUID kaiJuChuFangZhangHaoId = UUID.randomUUID();
    UUID queRenChuFangZhangHaoId = UUID.randomUUID();

    // 目前主要诊断
    String muQianZhuYaoZhenDuan = "m";

    Map<String, Object> xiangXiZhiLiaoJingGuoMap = PPUtil.stringToMap("A:1, B:1");
    Map<String, Object> jianChaZongJieMap = PPUtil.stringToMap("C:1, D:1");
    Map<String, Object> dianZiYingXiangMap = PPUtil.stringToMap("E:1, F:1");
    Map<String, Object> qiTaCaiLiaoMap = PPUtil.stringToMap("G:1, H:1");
    Map<String, Object> wenZhenZongJieMap = PPUtil.stringToMap("I:1, J:1");

    // 款
    WenZhen.YuFuKuan yuFuKuan = new WenZhen.YuFuKuan(
            "yfl", mockNow, new BigDecimal(10), "ybz"
    );
    List<WenZhen.BuChongKuan> buChongKuanList = new LinkedList<>(
            Arrays.asList(
                    new WenZhen.BuChongKuan(
                            "bcl",
                            mockNow,
                            "fkf",
                            "bz",
                            new BigDecimal(10),
                            1,
                            "bbz",
                            Arrays.asList("bp_1", "bp_2")
                    ),
                    new WenZhen.BuChongKuan(
                            "bcl2",
                            mockNow,
                            "fkf2",
                            "bz2",
                            new BigDecimal(20),
                            2,
                            "bbz",
                            Arrays.asList("bp2_1", "bp2_2")
                    )
            )
    );


    WenZhen.TuiKuan tuiKuan = new WenZhen.TuiKuan(
            "tkl",
            mockNow,
            "skzhm",
            "skzh",
            new BigDecimal("10"),
            "tkbz",
            Arrays.asList("tp_1", "tp_2")
    );

    WenZhen.TuiKuan tuiKuan2 = new WenZhen.TuiKuan(
            "tkl2",
            mockNow,
            "skzhm2",
            "skzh2",
            new BigDecimal("20"),
            "tkbz2",
            Arrays.asList("tp2_1", "tp2_2")
    );
    List<WenZhen.TuiKuan> tuiKuanList = new LinkedList<>(
            Arrays.asList(
                    tuiKuan,
                    tuiKuan2
            )
    );

    // mock data end

    private WenZhen getTemplate() {
        WenZhen template = new WenZhen();
        template.setId(id);
        template.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);
        template.setXiaDanShiJian(xiaDanShiJian);
        template.setYuFuFei(yuFuFei);
        template.setZongJia(zongJia);
        template.setChanPinId(chanPinId);
        template.setChanPinMingCheng(chanPinMingCheng);
        template.setChanPinJsonString(chanPinJsonString);
        template.setPaiBanId(paiBanId);
        template.setPaiBanJsonString(paiBanJsonString);

        // 付费状态
        template.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_YU_FU_FEI_ZHI_FU);

        // 会诊状态


        // 保险
        template.setBaoXianDanHao(baoXianDanHao);
        template.setXianZhongMingCheng(xianZhongMingCheng);

        // 健康档案
        template.setJianKangDangAnId(jianKangDangAnId);
        template.setJianKangDangAnMap(jianKangDangAnMap);

        // 医生
        template.setWenZhenZhangHaoId(wenZhenZhangHaoId);
        template.setBingLiBianJiZhangHaoId(bingLiBianJiZhangHaoId);
        template.setKaiJuChuFangZhangHaoId(kaiJuChuFangZhangHaoId);
        template.setQueRenChuFangZhangHaoId(queRenChuFangZhangHaoId);

        // 资料
        template.setMuQianZhuYaoZhenDuan(muQianZhuYaoZhenDuan);
        template.setXiangXiZhiLiaoJingGuoMap(xiangXiZhiLiaoJingGuoMap);
        template.setJianChaZongJieMap(jianChaZongJieMap);
        template.setDianZiYingXiangMap(dianZiYingXiangMap);
        template.setQiTaCaiLiaoMap(qiTaCaiLiaoMap);
        template.setWenZhenZongJieMap(wenZhenZongJieMap);

        // 款
        template.setYuFuKuan(yuFuKuan);
        template.setBuChongKuanList(buChongKuanList);
        template.setTuiKuanList(tuiKuanList);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(WenZhen.class);
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new WenZhen_ChuangJianCmd(
                        id,
                        jianKangDangAnId,
                        chanPinId,
                        paiBanId,
                        yuFuFei,
                        zongJia,
                        chanPinMingCheng,
                        chanPinJsonString,
                        paiBanJsonString,
                        jianKangDangAnMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_ChuangJianEvt(
                        id,
                        jianKangDangAnId,
                        chanPinId,
                        paiBanId,
                        yuFuFei,
                        zongJia,
                        chanPinMingCheng,
                        chanPinJsonString,
                        paiBanJsonString,
                        jianKangDangAnMap,
                        mockNow
                ))
                .expectState(state -> {
                    WenZhen record = new WenZhen();
                    record.setId(id);
                    record.setJianKangDangAnId(jianKangDangAnId);
                    record.setChanPinId(chanPinId);
                    record.setPaiBanId(paiBanId);
                    record.setYuFuFei(yuFuFei);
                    record.setZongJia(zongJia);
                    record.setChanPinMingCheng(chanPinMingCheng);
                    record.setChanPinJsonString(chanPinJsonString);
                    record.setPaiBanJsonString(paiBanJsonString);
                    record.setJianKangDangAnMap(jianKangDangAnMap);
                    record.setXiaDanShiJian(xiaDanShiJian);
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);
            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        yuFuKuan.getJinE()
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_ZhiFuYuFuKuanEvt(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        yuFuKuan.getJinE(),
                        mockNow
                ))
                .expectState(state -> {
                    WenZhen record = new WenZhen();
                    record.setId(id);
                    record.setJianKangDangAnId(jianKangDangAnId);
                    record.setChanPinId(chanPinId);
                    record.setPaiBanId(paiBanId);
                    record.setYuFuFei(yuFuFei);
                    record.setZongJia(zongJia);
                    record.setChanPinMingCheng(chanPinMingCheng);
                    record.setChanPinJsonString(chanPinJsonString);
                    record.setPaiBanJsonString(paiBanJsonString);
                    record.setJianKangDangAnMap(jianKangDangAnMap);
                    record.setXiaDanShiJian(xiaDanShiJian);
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);
                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_YU_FU_FEI_ZHI_FU);

                    record.setYuFuKuan(yuFuKuan);
                    // perform assertions
                    assertEquals(record, state);
                });
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        yuFuKuan.getJinE()
                ))
                .expectExceptionMessage("只有在已创建状态才能接收预付款");
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        new BigDecimal(yuFuFei.doubleValue() - 0.1)
                ))
                .expectExceptionMessage("预付款不足");
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        new BigDecimal(zongJia.doubleValue() + 0.1)
                ))
                .expectExceptionMessage("预付款不能超过总价");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_1() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setYuFuKuan(yuFuKuan);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiXingTuiKuanCmd(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        tuiKuan.getJinE(),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_ZhiXingTuiKuanEvt(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        tuiKuan.getJinE(),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectState(state -> {
                    WenZhen record = new WenZhen();
                    record.setId(id);
                    record.setJianKangDangAnId(jianKangDangAnId);
                    record.setChanPinId(chanPinId);
                    record.setPaiBanId(paiBanId);
                    record.setYuFuFei(yuFuFei);
                    record.setZongJia(zongJia);
                    record.setChanPinMingCheng(chanPinMingCheng);
                    record.setChanPinJsonString(chanPinJsonString);
                    record.setPaiBanJsonString(paiBanJsonString);
                    record.setJianKangDangAnMap(jianKangDangAnMap);
                    record.setXiaDanShiJian(xiaDanShiJian);

                    record.setYuFuKuan(yuFuKuan);

                    record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

                    record.getTuiKuanList().add(tuiKuan);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_2() {

        double balance = 0;
        balance += yuFuKuan.getJinE().doubleValue();

        for (WenZhen.BuChongKuan item : buChongKuanList) {
            balance += item.getJinE().doubleValue() * item.getFuKuanDangRiHuiLv();
        }

        for (WenZhen.TuiKuan item : tuiKuanList) {
            balance -= item.getJinE().doubleValue();
        }

        final BigDecimal balanceBigDecimal = new BigDecimal(balance);

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setYuFuKuan(yuFuKuan);
            record.setBuChongKuanList(buChongKuanList);
            record.setTuiKuanList(tuiKuanList);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiXingTuiKuanCmd(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        new BigDecimal(balance),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_ZhiXingTuiKuanEvt(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        new BigDecimal(balance),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectState(state -> {
                    WenZhen record = new WenZhen();
                    record.setId(id);
                    record.setJianKangDangAnId(jianKangDangAnId);
                    record.setChanPinId(chanPinId);
                    record.setPaiBanId(paiBanId);
                    record.setYuFuFei(yuFuFei);
                    record.setZongJia(zongJia);
                    record.setChanPinMingCheng(chanPinMingCheng);
                    record.setChanPinJsonString(chanPinJsonString);
                    record.setPaiBanJsonString(paiBanJsonString);
                    record.setJianKangDangAnMap(jianKangDangAnMap);
                    record.setXiaDanShiJian(xiaDanShiJian);

                    record.setYuFuKuan(yuFuKuan);
                    record.setBuChongKuanList(buChongKuanList);
                    record.setTuiKuanList(tuiKuanList);

                    record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

                    record.getTuiKuanList().add(
                            new WenZhen.TuiKuan(
                                    tuiKuan.getLiuShuiHao(),
                                    tuiKuan.getShiJian(),
                                    tuiKuan.getShouKuanZhangHuMing(),
                                    tuiKuan.getShouKuanZhangHu(),
                                    balanceBigDecimal,
                                    tuiKuan.getBeiZhu(),
                                    tuiKuan.getPingZheng()
                            )
                    );

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setYuFuKuan(yuFuKuan);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_ZhiXingTuiKuanCmd(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        tuiKuan.getJinE(),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectExceptionMessage("只有在已创建状态才能接收预付款");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setYuFuKuan(yuFuKuan);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiXingTuiKuanCmd(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        yuFuKuan.getJinE().add(new BigDecimal(0.1)),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectExceptionMessage("退款额度太大");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_3() {
        double balance = 0;
        balance += yuFuKuan.getJinE().doubleValue();

        for (WenZhen.BuChongKuan item : buChongKuanList) {
            balance += item.getJinE().doubleValue() * item.getFuKuanDangRiHuiLv();
        }

        for (WenZhen.TuiKuan item : tuiKuanList) {
            balance -= item.getJinE().doubleValue();
        }

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setYuFuKuan(yuFuKuan);

            record.setBuChongKuanList(buChongKuanList);
            record.setTuiKuanList(tuiKuanList);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhiXingTuiKuanCmd(
                        id,
                        tuiKuan.getLiuShuiHao(),
                        tuiKuan.getShiJian(),
                        tuiKuan.getShouKuanZhangHuMing(),
                        tuiKuan.getShouKuanZhangHu(),
                        new BigDecimal(balance += 0.1),
                        tuiKuan.getBeiZhu(),
                        tuiKuan.getPingZheng()
                ))
                .expectExceptionMessage("退款额度太大");
    }

    @Test
    public void test_WenZhen_JianKangDangAnGengXinCmd() {

        Map<String, Object> jianKangDangAnMap2 = PPUtil.stringToMap("B:2, C:2");

        fixture.givenState(() -> {
            WenZhen record = new WenZhen();
            record.setId(id);
            record.setJianKangDangAnId(jianKangDangAnId);
            record.setChanPinId(chanPinId);
            record.setPaiBanId(paiBanId);
            record.setYuFuFei(yuFuFei);
            record.setZongJia(zongJia);
            record.setChanPinMingCheng(chanPinMingCheng);
            record.setChanPinJsonString(chanPinJsonString);
            record.setPaiBanJsonString(paiBanJsonString);
            record.setJianKangDangAnMap(jianKangDangAnMap);
            record.setXiaDanShiJian(xiaDanShiJian);

            record.setYuFuKuan(yuFuKuan);

            record.setBuChongKuanList(buChongKuanList);
            record.setTuiKuanList(tuiKuanList);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_YU_FU_FEI_ZHI_FU);

            return record;
        })
                .when(new WenZhen_JianKangDangAnGengXinCmd(
                        id,
                        jianKangDangAnMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_JianKangDangAnGengXinEvt(
                        id,
                        jianKangDangAnMap2
                ))
                .expectState(state -> {
                    WenZhen record = new WenZhen();
                    record.setId(id);
                    record.setJianKangDangAnId(jianKangDangAnId);
                    record.setChanPinId(chanPinId);
                    record.setPaiBanId(paiBanId);
                    record.setYuFuFei(yuFuFei);
                    record.setZongJia(zongJia);
                    record.setChanPinMingCheng(chanPinMingCheng);
                    record.setChanPinJsonString(chanPinJsonString);
                    record.setPaiBanJsonString(paiBanJsonString);
                    record.setJianKangDangAnMap(jianKangDangAnMap);
                    record.setXiaDanShiJian(xiaDanShiJian);

                    record.setYuFuKuan(yuFuKuan);
                    record.setBuChongKuanList(buChongKuanList);
                    record.setTuiKuanList(tuiKuanList);

                    record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);
                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_YU_FU_FEI_ZHI_FU);

                    record.setJianKangDangAnMap(jianKangDangAnMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }
}
