package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
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

    // 会诊
    LocalDateTime huiZhenShiJian = mockNow;
    String huiZhenLianJie = "l";
    String huiZhenHuiYiId = "hId";
    String huiZhenHuanFangCanYuRenYuan = "r";
    String huiZhenBeiZhu = "b";
    String huiZhenShiPinLianJie = "sl";

    WenZhen.HuiZhen huiZhen = new WenZhen.HuiZhen(
            huiZhenShiJian,
            huiZhenLianJie,
            huiZhenHuiYiId,
            huiZhenHuanFangCanYuRenYuan,
            huiZhenBeiZhu,
            huiZhenShiPinLianJie
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
        template.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

        // 会诊状态
        template.setHuiZhenZhuangTai(null);

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
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);
            record.setYuFuKuan(null);
            record.setFuFeiZhuangTai(null);

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
                    WenZhen record = getTemplate();

                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);
                    record.setYuFuKuan(yuFuKuan);
                    // perform assertions
                    assertEquals(record, state);
                });
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
            record.setYuFuKuan(null);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        yuFuKuan.getJinE()
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已创建状态才能接收预付款");
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);
            record.setYuFuKuan(null);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        yuFuKuan.getJinE()
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在没有付费时才能接收预付款");
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(null);
            record.setYuFuKuan(null);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        new BigDecimal(yuFuFei.doubleValue() - 0.1)
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("预付款不足");
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhen_ZhiFuYuFuKuanCmd_失败_4() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setYuFuKuan(null);
            record.setFuFeiZhuangTai(null);

            return record;
        })
                .when(new WenZhen_ZhiFuYuFuKuanCmd(
                        id,
                        yuFuKuan.getLiuShuiHao(),
                        yuFuKuan.getBeiZhu(),
                        new BigDecimal(zongJia.doubleValue() + 0.1)
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("预付款不能超过总价");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

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
                    WenZhen record = getTemplate();

                    record.getTuiKuanList().add(tuiKuan);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
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
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已创建状态才能退款");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(null);

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
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付预付费状态才能退款");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setTuiKuanList(new LinkedList<>());
            record.setBuChongKuanList(new LinkedList<>());

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
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("退款额度太大");
    }

    @Test
    public void test_WenZhen_ZhiXingTuiKuanCmd_失败_4() {
        double balance = 0;
        balance += yuFuKuan.getJinE().doubleValue();

        for (WenZhen.BuChongKuan item : buChongKuanList) {
            balance += item.getJinE().doubleValue() * item.getFuKuanDangRiHuiLv();
        }

        for (WenZhen.TuiKuan item : tuiKuanList) {
            balance -= item.getJinE().doubleValue();
        }

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

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
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("退款额度太大");
    }

    @Test
    public void test_WenZhen_JianKangDangAnGengXinCmd() {

        Map<String, Object> jianKangDangAnMap2 = PPUtil.stringToMap("B:2, C:2");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinJianKangDangAnCmd(
                        id,
                        jianKangDangAnMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_GengXinJianKangDangAnEvt(
                        id,
                        jianKangDangAnMap2
                ))
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setJianKangDangAnMap(jianKangDangAnMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_JianKangDangAnGengXinCmd_失败_1() {

        Map<String, Object> jianKangDangAnMap2 = PPUtil.stringToMap("B:2, C:2");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHENG_GONG_WAN_CHENG);

            return record;
        })
                .when(new WenZhen_GengXinJianKangDangAnCmd(
                        id,
                        jianKangDangAnMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在非已完成状态才能更新健康档案");
    }

    @Test
    public void test_WenZhen_JianKangDangAnGengXinCmd_失败_2() {

        Map<String, Object> jianKangDangAnMap2 = PPUtil.stringToMap("B:2, C:2");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_ZHONG_DUAN_WAN_CHENG);

            return record;
        })
                .when(new WenZhen_GengXinJianKangDangAnCmd(
                        id,
                        jianKangDangAnMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在非已完成状态才能更新健康档案");
    }

    @Test
    public void test_WenZhen_JianKangDangAnGengXinCmd_失败_3() {

        Map<String, Object> jianKangDangAnMap2 = PPUtil.stringToMap("B:2, C:2");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(null);

            return record;
        })
                .when(new WenZhen_GengXinJianKangDangAnCmd(
                        id,
                        jianKangDangAnMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已付费状态才能更新健康档案");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_1() {

        WenZhen.BuChongKuan buChongKuan3 = new WenZhen.BuChongKuan(
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(10),
                2,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhen_ZhiFuBuChongKuanEvt(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.getBuChongKuanList().add(buChongKuan3);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_2() {

        WenZhen record = getTemplate();
        double huiLv = 2;
        double leftJinE = (record.getZongJia().doubleValue() - record.jiSuanBlance()) / huiLv;

        WenZhen.BuChongKuan buChongKuan3 = new WenZhen.BuChongKuan(
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(leftJinE / huiLv),
                huiLv,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        fixture.givenState(() -> {
            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_ZhiFuBuChongKuanEvt(
                                id,
                                buChongKuan3.getLiuShuiHao(),
                                buChongKuan3.getShiJian(),
                                buChongKuan3.getFuKuanFang(),
                                buChongKuan3.getBiZhong(),
                                buChongKuan3.getJinE(),
                                buChongKuan3.getFuKuanDangRiHuiLv(),
                                buChongKuan3.getBeiZhu(),
                                buChongKuan3.getPingZheng()
                        ),
                        new WenZhen_ZhiFuQuanKuanEvt(
                                id
                        )
                )
                .expectState(state -> {
                    WenZhen record2 = getTemplate();

                    record2.getBuChongKuanList().add(buChongKuan3);
                    record2.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_1() {

        WenZhen.BuChongKuan buChongKuan3 = new WenZhen.BuChongKuan(
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(10),
                2,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHENG_GONG_WAN_CHENG);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在非已完成状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_2() {

        WenZhen.BuChongKuan buChongKuan3 = new WenZhen.BuChongKuan(
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(10),
                2,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_ZHONG_DUAN_WAN_CHENG);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在非已完成状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_3() {

        WenZhen.BuChongKuan buChongKuan3 = new WenZhen.BuChongKuan(
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(10),
                2,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(null);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付预付费状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_ZhiFuBuChongKuanCmd_失败_4() {

        WenZhen.BuChongKuan buChongKuan3 = new WenZhen.BuChongKuan(
                "bcl3",
                mockNow,
                "fkf3",
                "bz3",
                new BigDecimal(10),
                2,
                "bbz3",
                Arrays.asList("bp3_1", "bp3_2")
        );

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

            return record;
        })
                .when(new WenZhen_ZhiFuBuChongKuanCmd(
                        id,
                        buChongKuan3.getLiuShuiHao(),
                        buChongKuan3.getShiJian(),
                        buChongKuan3.getFuKuanFang(),
                        buChongKuan3.getBiZhong(),
                        buChongKuan3.getJinE(),
                        buChongKuan3.getFuKuanDangRiHuiLv(),
                        buChongKuan3.getBeiZhu(),
                        buChongKuan3.getPingZheng()
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付预付费状态才能支付补充款");
    }

    @Test
    public void test_WenZhen_AnPaiYiShengCmd() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setWenZhenZhangHaoId(null);
            record.setBingLiBianJiZhangHaoId(null);
            record.setKaiJuChuFangZhangHaoId(null);
            record.setQueRenChuFangZhangHaoId(null);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

            return record;
        })
                .when(new WenZhen_AnPaiYiShengCmd(
                        id,
                        wenZhenZhangHaoId,
                        bingLiBianJiZhangHaoId,
                        kaiJuChuFangZhangHaoId,
                        queRenChuFangZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_AnPaiYiShengEvt(
                                id,
                                wenZhenZhangHaoId,
                                bingLiBianJiZhangHaoId,
                                kaiJuChuFangZhangHaoId,
                                queRenChuFangZhangHaoId

                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_AnPaiYiShengCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setWenZhenZhangHaoId(null);
            record.setBingLiBianJiZhangHaoId(null);
            record.setKaiJuChuFangZhangHaoId(null);
            record.setQueRenChuFangZhangHaoId(null);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_AnPaiYiShengCmd(
                        id,
                        wenZhenZhangHaoId,
                        bingLiBianJiZhangHaoId,
                        kaiJuChuFangZhangHaoId,
                        queRenChuFangZhangHaoId
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已创建状态才能安排医生");
    }

    @Test
    public void test_WenZhen_AnPaiYiShengCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setWenZhenZhangHaoId(null);
            record.setBingLiBianJiZhangHaoId(null);
            record.setKaiJuChuFangZhangHaoId(null);
            record.setQueRenChuFangZhangHaoId(null);

            record.setFuFeiZhuangTai(null);

            return record;
        })
                .when(new WenZhen_AnPaiYiShengCmd(
                        id,
                        wenZhenZhangHaoId,
                        bingLiBianJiZhangHaoId,
                        kaiJuChuFangZhangHaoId,
                        queRenChuFangZhangHaoId
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付全款状态才能安排医生");
    }

    @Test
    public void test_WenZhen_ZhuanZhenCmd() {
        UUID wenZhenZhangHaoId2 = UUID.randomUUID();
        UUID bingLiBianJiZhangHaoId2 = UUID.randomUUID();
        UUID kaiJuChuFangZhangHaoId2 = UUID.randomUUID();
        UUID queRenChuFangZhangHaoId2 = UUID.randomUUID();


        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

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
                .expectEvents(
                        new WenZhen_ZhuanZhenEvt(
                                id,
                                wenZhenZhangHaoId2,
                                bingLiBianJiZhangHaoId2,
                                kaiJuChuFangZhangHaoId2,
                                queRenChuFangZhangHaoId2

                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
                    record.setWenZhenZhangHaoId(wenZhenZhangHaoId2);
                    record.setBingLiBianJiZhangHaoId(bingLiBianJiZhangHaoId2);
                    record.setKaiJuChuFangZhangHaoId(kaiJuChuFangZhangHaoId2);
                    record.setQueRenChuFangZhangHaoId(queRenChuFangZhangHaoId2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_ZhuanZhenCmd_失败() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setWenZhenZhangHaoId(null);
            record.setBingLiBianJiZhangHaoId(null);
            record.setKaiJuChuFangZhangHaoId(null);
            record.setQueRenChuFangZhangHaoId(null);

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_ZhuanZhenCmd(
                        id,
                        wenZhenZhangHaoId,
                        bingLiBianJiZhangHaoId,
                        kaiJuChuFangZhangHaoId,
                        queRenChuFangZhangHaoId
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能转诊");
    }

    @Test
    public void test_WenZhen_AnPaiHuiZhenCmd() {

        WenZhen.HuiZhen huiZhen2 = huiZhen;
        huiZhen2.setShiPinLianJie(null);

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

            return record;
        })
                .when(new WenZhen_AnPaiHuiZhenCmd(
                        id,
                        huiZhenShiJian,
                        huiZhenLianJie,
                        huiZhenHuiYiId,
                        huiZhenHuanFangCanYuRenYuan,
                        huiZhenBeiZhu
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_AnPaiHuiZhenEvt(
                                id,
                                huiZhenShiJian,
                                huiZhenLianJie,
                                huiZhenHuiYiId,
                                huiZhenHuanFangCanYuRenYuan,
                                huiZhenBeiZhu
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
                    record.setHuiZhenZhuangTai(WenZhen.HuiZhenZhuangTai.YI_AN_PAI);
                    record.setHuiZhen(huiZhen2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_AnPaiHuiZhenCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_AnPaiHuiZhenCmd(
                        id,
                        huiZhenShiJian,
                        huiZhenLianJie,
                        huiZhenHuiYiId,
                        huiZhenHuanFangCanYuRenYuan,
                        huiZhenBeiZhu
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能安排会诊");
    }

    @Test
    public void test_WenZhen_AnPaiHuiZhenCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_AnPaiHuiZhenCmd(
                        id,
                        huiZhenShiJian,
                        huiZhenLianJie,
                        huiZhenHuiYiId,
                        huiZhenHuanFangCanYuRenYuan,
                        huiZhenBeiZhu
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付全款状态才能安排会诊");
    }

    @Test
    public void test_WenZhen_AnPaiHuiZhenCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
            record.setHuiZhenZhuangTai(WenZhen.HuiZhenZhuangTai.YI_AN_PAI);

            return record;
        })
                .when(new WenZhen_AnPaiHuiZhenCmd(
                        id,
                        huiZhenShiJian,
                        huiZhenLianJie,
                        huiZhenHuiYiId,
                        huiZhenHuanFangCanYuRenYuan,
                        huiZhenBeiZhu
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在没有安排会诊时才能安排会诊");
    }

    @Test
    public void test_WenZhen_SheZhiHuiZhenShiPinCmd() {

        String huiZhenShiPinLianJie2 = "sl2";

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
            record.setHuiZhenZhuangTai(WenZhen.HuiZhenZhuangTai.YI_AN_PAI);
            record.setHuiZhen(huiZhen);

            return record;
        })
                .when(new WenZhen_SheZhiHuiZhenShiPinCmd(
                        id,
                        huiZhenShiPinLianJie2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_SheZhiHuiZhenShiPinEvt(
                                id,
                                huiZhenShiPinLianJie2
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();

                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
                    record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
                    record.setHuiZhenZhuangTai(WenZhen.HuiZhenZhuangTai.YI_AN_PAI);
                    huiZhen.setShiPinLianJie(huiZhenShiPinLianJie2);
                    record.setHuiZhen(huiZhen);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_SheZhiHuiZhenShiPinCmd_失败_1() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

            return record;
        })
                .when(new WenZhen_SheZhiHuiZhenShiPinCmd(
                        id,
                        huiZhenShiPinLianJie
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能设置会诊视频链接");
    }

    @Test
    public void test_WenZhen_SheZhiHuiZhenShiPinCmd_失败_2() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_SheZhiHuiZhenShiPinCmd(
                        id,
                        huiZhenShiPinLianJie
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已支付全款状态才能设置会诊视频链接");
    }

    @Test
    public void test_WenZhen_SheZhiHuiZhenShiPinCmd_失败_3() {

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);
            record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

            return record;
        })
                .when(new WenZhen_SheZhiHuiZhenShiPinCmd(
                        id,
                        huiZhenShiPinLianJie
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在安排会诊后才能设置会诊视频链接");
    }

    @Test
    public void test_WenZhen_GengXinMuQianZhuYaoZhenDuanCmd() {

        String zhenDuan2 = "m2";

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
                        id,
                        zhenDuan2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_GengXinMuQianZhuYaoZhenDuanEvt(
                                id,
                                zhenDuan2

                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    record.setMuQianZhuYaoZhenDuan(zhenDuan2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_GengXinMuQianZhuYaoZhenDuanCmd_失败() {

        String zhenDuan2 = "m2";

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
                        id,
                        zhenDuan2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能递交目前主要诊断");
    }

    @Test
    public void test_WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt(
                                id,
                                xiangXiZhiLiaoJingGuoMap2
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    record.setXiangXiZhiLiaoJingGuoMap(xiangXiZhiLiaoJingGuoMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd_失败() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新详细治疗经过");
    }

    @Test
    public void test_WenZhen_GengXinJianChaZongJieCmd() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_GengXinJianChaZongJieCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_GengXinJianChaZongJieEvt(
                                id,
                                xiangXiZhiLiaoJingGuoMap2
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    record.setJianChaZongJieMap(xiangXiZhiLiaoJingGuoMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_GengXinJianChaZongJieCmd_失败() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinJianChaZongJieCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新检查总结");
    }

    @Test
    public void test_WenZhen_GengXinDianZiYingXiangCmd() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_GengXinDianZiYingXiangCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_GengXinDianZiYingXiangEvt(
                                id,
                                xiangXiZhiLiaoJingGuoMap2
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    record.setDianZiYingXiangMap(xiangXiZhiLiaoJingGuoMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_GengXinDianZiYingXiangCmd_失败() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinDianZiYingXiangCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新电子影像");
    }

    @Test
    public void test_WenZhen_GengXinQiTaCaiLiaoCmd() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_GengXinQiTaCaiLiaoCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_GengXinQiTaCaiLiaoEvt(
                                id,
                                xiangXiZhiLiaoJingGuoMap2
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    record.setQiTaCaiLiaoMap(xiangXiZhiLiaoJingGuoMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_GengXinQiTaCaiLiaoCmd_失败() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinQiTaCaiLiaoCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新其他材料");
    }

    @Test
    public void test_WenZhen_GengXinWenZhenZongJieCmd() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();
            record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

            return record;
        })
                .when(new WenZhen_GengXinWenZhenZongJieCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new WenZhen_GengXinWenZhenZongJieEvt(
                                id,
                                xiangXiZhiLiaoJingGuoMap2
                        )
                )
                .expectState(state -> {
                    WenZhen record = getTemplate();
                    record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

                    record.setWenZhenZongJieMap(xiangXiZhiLiaoJingGuoMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_WenZhen_GengXinWenZhenZongJieCmd_失败() {

        Map<String, Object> xiangXiZhiLiaoJingGuoMap2 = PPUtil.stringToMap("XA:1, XB: 1");

        fixture.givenState(() -> {
            WenZhen record = getTemplate();

            return record;
        })
                .when(new WenZhen_GengXinWenZhenZongJieCmd(
                        id,
                        xiangXiZhiLiaoJingGuoMap2
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("只有在已安排医生状态才能更新问诊总结");
    }
}
