package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.PPAggregate;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.BiZhong;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class WenZhen extends PPAggregate {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class YuFuKuan {
        String liuShuiHao;
        OffsetDateTime shiJian;
        BigDecimal jinE;
        String beiZhu;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class BuChongKuan {
        String liuShuiHao;
        OffsetDateTime shiJian;
        String fuKuanFang;
        BiZhong biZhong;
        BigDecimal jinE;
        double fuKuanDangRiHuiLv;
        String beiZhu;
        String pingZhengList;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TuiKuan {
        String liuShuiHao;
        OffsetDateTime shiJian;
        String shouKuanZhangHuMing;
        String shouKuanZhangHu;
        BigDecimal jinE;
        String beiZhu;
        String pingZhengList;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class HuiZhen {
        HuiZhenZhuangTai zhuangTai;
        OffsetDateTime shiJian;
        String lianJie;
        String huiYiId;
        String huanFangCanYuRenYuan;
        String beiZhu;

        String shiPinLianJie;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class WenZhenBaoGao {
        UUID id;
        String zhengWen;
        UUID zhangHaoId;
        OffsetDateTime shiJian;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ZhenLiaoBaoGao {
        UUID id;
        String zhengWen;
        UUID zhangHaoId;
        OffsetDateTime shiJian;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ChuFang {
        ChuFangZhuangTai zhuangTai;
        String zhengWen;

        UUID kaiJuZhangHaoId;
        OffsetDateTime kaiJuShiJian;

        UUID queRenZhangHaoId;
        OffsetDateTime queRenShiJian;

        UUID quXiaoZhangHaoId;
    }

    @AggregateIdentifier
    UUID id;

    ZhuangTai zhuangTai;
    String wanChengBeiZhu;

    FuFeiZhuangTai fuFeiZhuangTai;

    OffsetDateTime xiaDanShiJian;
    BigDecimal yuFuFei;
    BigDecimal zongJia;

    // 产品相关
    UUID chanPinId;
    String chanPinMingCheng;
    String chanPinJsonString;

    // 排班相关
    UUID paiBanId;
    String paiBanJsonString;

    // 保险相关
    String baoXianDanHao;
    String xianZhongMingCheng;

    // 健康档案相关
    UUID jianKangDangAnId;
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jianKangDangAnMap;

    // 医护人员相关
    UUID wenZhenZhangHaoId;
    UUID bingLiBianJiZhangHaoId;
    UUID kaiJuChuFangZhangHaoId;
    UUID queRenChuFangZhangHaoId;

    // 目前主要诊断
    String muQianZhuYaoZhenDuan;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xiangXiZhiLiaoJingGuoMap;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jianChaZongJieMap;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> dianZiYingXiangMap;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> qiTaCaiLiaoMap;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> wenZhenZongJieMap;

    // 款
    YuFuKuan yuFuKuan;
    List<BuChongKuan> buChongKuanList = new LinkedList<>();
    List<TuiKuan> tuiKuanList = new LinkedList<>();

    // 会诊
    HuiZhen huiZhen;

    // 处方
    ChuFang chuFang;

    // 报告
    List<WenZhenBaoGao> wenZhenBaoGaoList = new LinkedList<>();
    List<ZhenLiaoBaoGao> zhenLiaoBaoGaoList = new LinkedList<>();

    public double jiSuanBlance() {
        double balance = 0;
        if (yuFuKuan != null) {
            balance += yuFuKuan.getJinE().doubleValue();
        }

        if (buChongKuanList != null) {
            for (BuChongKuan item : buChongKuanList) {
                balance += item.getJinE().doubleValue() * item.getFuKuanDangRiHuiLv();
            }
        }

        if (tuiKuanList != null) {
            for (TuiKuan item : tuiKuanList) {
                balance -= item.getJinE().doubleValue();
            }
        }

        return balance;
    }

    @CommandHandler
    public WenZhen(WenZhen_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态


        // 条件检查 end

        apply(
                new WenZhen_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getJianKangDangAnId(),
                        cmd.getChanPinId(),
                        cmd.getPaiBanId(),
                        cmd.getYuFuFei(),
                        cmd.getZongJia(),
                        cmd.getChanPinMingCheng(),
                        cmd.getChanPinJsonString(),
                        cmd.getPaiBanJsonString(),
                        cmd.getJianKangDangAnMap(),
                        OffsetDateTime.now()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.jianKangDangAnId = evt.getJianKangDangAnId();
        this.chanPinId = evt.getChanPinId();
        this.paiBanId = evt.getPaiBanId();
        this.yuFuFei = evt.getYuFuFei();
        this.zongJia = evt.getZongJia();
        this.chanPinMingCheng = evt.getChanPinMingCheng();

        this.chanPinJsonString = evt.getChanPinJsonString();
        this.paiBanJsonString = evt.getPaiBanJsonString();

        this.jianKangDangAnMap = evt.getJianKangDangAnMap();

        this.xiaDanShiJian = evt.getXiaDanShiJian();

        this.zhuangTai = ZhuangTai.YI_CHUANG_JIAN;

        this.fuFeiZhuangTai = FuFeiZhuangTai.WEI_FU_FEI;
    }

    @CommandHandler
    public void on(WenZhen_ZhiFuYuFuKuanCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (!(zhuangTai == ZhuangTai.YI_CHUANG_JIAN)) {
            throw new PPBusinessException("只有在已创建状态才能接收预付款");
        }

        // 付费状态
        if (!(fuFeiZhuangTai == FuFeiZhuangTai.WEI_FU_FEI)) {
            throw new PPBusinessException("只有在没有付费时才能接收预付款");
        }

        // 金额
        if (!(cmd.getJinE().doubleValue() >= yuFuFei.doubleValue())) {
            throw new PPBusinessException("预付款不足");
        }

        if (!(cmd.getJinE().doubleValue() <= zongJia.doubleValue())) {
            throw new PPBusinessException("预付款不能超过总价");
        }

        // 条件检查 end

        apply(
                new WenZhen_ZhiFuYuFuKuanEvt(
                        cmd.getId(),
                        cmd.getLiuShuiHao(),
                        cmd.getBeiZhu(),
                        cmd.getJinE(),
                        OffsetDateTime.now()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_ZhiFuYuFuKuanEvt evt) {
        this.yuFuKuan = new YuFuKuan(
                evt.getLiuShuiHao(),
                evt.getShiJian(),
                evt.getJinE(),
                evt.getBeiZhu()
        );

        fuFeiZhuangTai = FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI;
    }

    @CommandHandler
    public void on(WenZhen_ZhiXingTuiKuanCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (!(zhuangTai == ZhuangTai.YI_CHUANG_JIAN)) {
            throw new PPBusinessException("只有在已创建状态才能退款");
        }

        // 付费状态
        if (!(fuFeiZhuangTai == FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI)) {
            throw new PPBusinessException("只有在已支付预付费状态才能退款");
        }

        // 金额
        // balance
        if (!(jiSuanBlance() >= cmd.getJinE().doubleValue())) {
            throw new PPBusinessException("退款额度太大");
        }
        // 金额 end

        // 流水号当前list中不能重复
        for(TuiKuan item: tuiKuanList) {
            if (!(item.getLiuShuiHao() != cmd.getLiuShuiHao())) {
                throw new PPBusinessException("流水号不能重复");
            }
        }

        // 条件检查 end

        apply(
                new WenZhen_ZhiXingTuiKuanEvt(
                        cmd.getId(),
                        cmd.getLiuShuiHao(),
                        cmd.getShiJian(),
                        cmd.getShouKuanZhangHuMing(),
                        cmd.getShouKuanZhangHu(),
                        cmd.getJinE(),
                        cmd.getBeiZhu(),
                        cmd.getPingZhengList()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_ZhiXingTuiKuanEvt evt) {
        TuiKuan tuiKuan = new TuiKuan(
                evt.getLiuShuiHao(),
                evt.getShiJian(),
                evt.getShouKuanZhangHuMing(),
                evt.getShouKuanZhangHu(),
                evt.getJinE(),
                evt.getBeiZhu(),
                Strings.join(evt.getPingZhengList(), ',')
        );

        this.tuiKuanList.add(tuiKuan);
    }

    @CommandHandler
    public void on(WenZhen_GengXinJianKangDangAnCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (!(zhuangTai != ZhuangTai.YI_CHENG_GONG_WAN_CHENG && zhuangTai != ZhuangTai.YI_JIE_SHU_WAN_CHENG)) {
            throw new PPBusinessException("只有在非已完成状态才能更新健康档案");
        }

        // 付费状态
        if (!(fuFeiZhuangTai != FuFeiZhuangTai.WEI_FU_FEI)) {
            throw new PPBusinessException("只有在已付费状态才能更新健康档案");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinJianKangDangAnEvt(
                        cmd.getId(),
                        cmd.getJianKangDangAnMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinJianKangDangAnEvt evt) {
        this.jianKangDangAnMap = evt.getJianKangDangAnMap();
    }

    @CommandHandler
    public void on(WenZhen_ZhiFuBuChongKuanCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai == ZhuangTai.YI_CHENG_GONG_WAN_CHENG || zhuangTai == ZhuangTai.YI_JIE_SHU_WAN_CHENG) {
            throw new PPBusinessException("只有在非已完成状态才能支付补充款");
        }

        // 付费状态
        if (fuFeiZhuangTai != FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI) {
            throw new PPBusinessException("只有在已支付预付费状态才能支付补充款");
        }

        // 流水号当前list中不能重复
        for(BuChongKuan item: buChongKuanList) {
            if (item.getLiuShuiHao() == cmd.getLiuShuiHao()) {
                throw new PPBusinessException("流水号不能重复");
            }
        }

        // 条件检查 end
        boolean finished = false;

        // 这里一定要预先判断, 如果在if (finished)处判断, 会导致在WenZhen_ZhiFuBuChongKuanEvt生效后计算balance从而重复计算当前这一笔
        if (jiSuanBlance() + cmd.getFuKuanDangRiHuiLv() * cmd.getJinE().doubleValue() >= zongJia.doubleValue()) {
           finished = true;
        }

        apply(
                new WenZhen_ZhiFuBuChongKuanEvt(
                        cmd.getId(),
                        cmd.getLiuShuiHao(),
                        cmd.getShiJian(),
                        cmd.getFuKuanFang(),
                        cmd.getBiZhong(),
                        cmd.getJinE(),
                        cmd.getFuKuanDangRiHuiLv(),
                        cmd.getBeiZhu(),
                        cmd.getPingZhengList()
                ),
                metaData
        );

        if (finished) {
            apply(
                    new WenZhen_ZhiFuQuanKuanEvt(
                            cmd.getId()
                    )
            );
        }
    }

    @EventSourcingHandler
    public void on(WenZhen_ZhiFuBuChongKuanEvt evt) {
        BuChongKuan buChongKuan = new BuChongKuan(
                evt.getLiuShuiHao(),
                evt.getShiJian(),
                evt.getFuKuanFang(),
                evt.getBiZhong(),
                evt.getJinE(),
                evt.getFuKuanDangRiHuiLv(),
                evt.getBeiZhu(),
                Strings.join(evt.getPingZhengList(), ',')
        );

        this.buChongKuanList.add(buChongKuan);
    }

    @EventSourcingHandler
    public void on(WenZhen_ZhiFuQuanKuanEvt evt) {
        this.setFuFeiZhuangTai(FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);
    }

    @CommandHandler
    public void on(WenZhen_AnPaiYiShengCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_CHUANG_JIAN) {
            throw new PPBusinessException("只有在已创建状态才能安排医生");
        }

        // 付费状态
        if (fuFeiZhuangTai != FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN) {
            throw new PPBusinessException("只有在已支付全款状态才能安排医生");
        }

        // 条件检查 end

        apply(
                new WenZhen_AnPaiYiShengEvt(
                        cmd.getId(),
                        cmd.getWenZhenZhangHaoId(),
                        cmd.getBingLiBianJiZhangHaoId(),
                        cmd.getKaiJuChuFangZhangHaoId(),
                        cmd.getQueRenChuFangZhangHaoId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_AnPaiYiShengEvt evt) {
        this.wenZhenZhangHaoId = evt.getWenZhenZhangHaoId();
        this.bingLiBianJiZhangHaoId = evt.getBingLiBianJiZhangHaoId();
        this.kaiJuChuFangZhangHaoId = evt.getKaiJuChuFangZhangHaoId();
        this.queRenChuFangZhangHaoId = evt.getQueRenChuFangZhangHaoId();

        this.zhuangTai = ZhuangTai.YI_AN_PAI_YI_SHENG;
    }

    @CommandHandler
    public void on(WenZhen_ZhuanZhenCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能转诊");
        }

        // 条件检查 end

        apply(
                new WenZhen_ZhuanZhenEvt(
                        cmd.getId(),
                        cmd.getWenZhenZhangHaoId(),
                        cmd.getBingLiBianJiZhangHaoId(),
                        cmd.getKaiJuChuFangZhangHaoId(),
                        cmd.getQueRenChuFangZhangHaoId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_ZhuanZhenEvt evt) {
        this.wenZhenZhangHaoId = evt.getWenZhenZhangHaoId();
        this.bingLiBianJiZhangHaoId = evt.getBingLiBianJiZhangHaoId();
        this.kaiJuChuFangZhangHaoId = evt.getKaiJuChuFangZhangHaoId();
        this.queRenChuFangZhangHaoId = evt.getQueRenChuFangZhangHaoId();
    }

    @CommandHandler
    public void on(WenZhen_AnPaiHuiZhenCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (!(zhuangTai == ZhuangTai.YI_AN_PAI_YI_SHENG)) {
            throw new PPBusinessException("只有在已安排医生状态才能安排会诊");
        }

        // 付费状态
        if (!(fuFeiZhuangTai == FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN)) {
            throw new PPBusinessException("只有在已支付全款状态才能安排会诊");
        }

        // 会诊状态
        if (!(this.huiZhen == null || this.huiZhen.zhuangTai != HuiZhenZhuangTai.YI_WAN_CHENG)) {
            throw new PPBusinessException("只有在没有完成会诊时才能安排会诊");
        }

        // 条件检查 end

        apply(
                new WenZhen_AnPaiHuiZhenEvt(
                        cmd.getId(),
                        cmd.getShiJian(),
                        cmd.getLianJie(),
                        cmd.getHuiYiId(),
                        cmd.getHuanFangCanYuRenYuan(),
                        cmd.getBeiZhu()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_AnPaiHuiZhenEvt evt) {
        HuiZhen huiZhen = new HuiZhen(
                HuiZhenZhuangTai.YI_AN_PAI,
                evt.getShiJian(),
                evt.getLianJie(),
                evt.getHuiYiId(),
                evt.getHuanFangCanYuRenYuan(),
                evt.getBeiZhu(),
                null
        );

        this.huiZhen = huiZhen;
    }

    @CommandHandler
    public void on(WenZhen_SheZhiHuiZhenShiPinCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (!(zhuangTai == ZhuangTai.YI_AN_PAI_YI_SHENG)) {
            throw new PPBusinessException("只有在已安排医生状态才能设置会诊视频链接");
        }

        // 付费状态
        if (!(fuFeiZhuangTai == FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN)) {
            throw new PPBusinessException("只有在已支付全款状态才能设置会诊视频链接");
        }

        // 会诊状态
        if (!(this.huiZhen != null && this.huiZhen.zhuangTai == HuiZhenZhuangTai.YI_AN_PAI)) {
            throw new PPBusinessException("只有在安排会诊后才能设置会诊视频链接");
        }

        // 条件检查 end

        apply(
                new WenZhen_SheZhiHuiZhenShiPinEvt(
                        cmd.getId(),
                        cmd.getShiPinLianJie()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_SheZhiHuiZhenShiPinEvt evt) {
        this.getHuiZhen().setShiPinLianJie(evt.getShiPinLianJie());
    }

    @CommandHandler
    public void on(WenZhen_GengXinMuQianZhuYaoZhenDuanCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能递交目前主要诊断");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinMuQianZhuYaoZhenDuanEvt(
                        cmd.getId(),
                        cmd.getZhenDuan()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinMuQianZhuYaoZhenDuanEvt evt) {
        this.muQianZhuYaoZhenDuan = evt.getZhenDuan();
    }

    @CommandHandler
    public void on(WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能更新详细治疗经过");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt(
                        cmd.getId(),
                        cmd.getXiangXiZhiLiaoJingGuoMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt evt) {
        this.xiangXiZhiLiaoJingGuoMap = evt.getXiangXiZhiLiaoJingGuoMap();
    }

    @CommandHandler
    public void on(WenZhen_GengXinJianChaZongJieCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能更新检查总结");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinJianChaZongJieEvt(
                        cmd.getId(),
                        cmd.getJianChaZongJieMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinJianChaZongJieEvt evt) {
        this.jianChaZongJieMap = evt.getJianChaZongJieMap();
    }

    @CommandHandler
    public void on(WenZhen_GengXinDianZiYingXiangCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能更新电子影像");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinDianZiYingXiangEvt(
                        cmd.getId(),
                        cmd.getDianZiYingXiangMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinDianZiYingXiangEvt evt) {
        this.dianZiYingXiangMap = evt.getDianZiYingXiangMap();
    }

    @CommandHandler
    public void on(WenZhen_GengXinQiTaCaiLiaoCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能更新其他材料");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinQiTaCaiLiaoEvt(
                        cmd.getId(),
                        cmd.getQiTaCaiLiaoMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinQiTaCaiLiaoEvt evt) {
        this.qiTaCaiLiaoMap = evt.getQiTaCaiLiaoMap();
    }

    @CommandHandler
    public void on(WenZhen_GengXinWenZhenZongJieCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生状态才能更新问诊总结");
        }

        // 条件检查 end

        apply(
                new WenZhen_GengXinWenZhenZongJieEvt(
                        cmd.getId(),
                        cmd.getWenZhenZongJieMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_GengXinWenZhenZongJieEvt evt) {
        this.wenZhenZongJieMap = evt.getWenZhenZongJieMap();
    }

    @CommandHandler
    public void on(WenZhen_ChengGongWanChengCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai == ZhuangTai.YI_CHENG_GONG_WAN_CHENG || zhuangTai == ZhuangTai.YI_JIE_SHU_WAN_CHENG) {
            throw new PPBusinessException("只有在未完成时才能完成问诊");
        }

        // 条件检查 end

        apply(
                new WenZhen_ChengGongWanChengEvt(
                        cmd.getId(),
                        cmd.getBeiZhu()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_ChengGongWanChengEvt evt) {
        this.wanChengBeiZhu = evt.getBeiZhu();

        this.zhuangTai = ZhuangTai.YI_CHENG_GONG_WAN_CHENG;
    }

    @CommandHandler
    public void on(WenZhen_JieShuWanChengCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai == ZhuangTai.YI_CHENG_GONG_WAN_CHENG || zhuangTai == ZhuangTai.YI_JIE_SHU_WAN_CHENG) {
            throw new PPBusinessException("只有在未完成时才能完成问诊");
        }

        // 条件检查 end

        apply(
                new WenZhen_JieShuWanChengEvt(
                        cmd.getId(),
                        cmd.getBeiZhu()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_JieShuWanChengEvt evt) {
        this.wanChengBeiZhu = evt.getBeiZhu();

        this.zhuangTai = ZhuangTai.YI_JIE_SHU_WAN_CHENG;
    }

    @CommandHandler
    public void on(WenZhen_KaiJuWenZhenBaoGaoCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生时才能创建问诊报告");
        }

        // 条件检查 end

        apply(
                new WenZhen_KaiJuWenZhenBaoGaoEvt(
                        cmd.getId(),
                        UUID.randomUUID(),
                        cmd.getZhengWen(),
                        cmd.getZhangHaoId(),
                        OffsetDateTime.now()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_KaiJuWenZhenBaoGaoEvt evt) {
        WenZhenBaoGao record = new WenZhenBaoGao();
        record.setId(evt.getWenZhenBaoGaoId());
        record.setZhengWen(evt.getZhengWen());
        record.setZhangHaoId(evt.getZhangHaoId());
        record.setShiJian(evt.getShiJian());

        this.wenZhenBaoGaoList.add(record);
    }

    @CommandHandler
    public void on(WenZhen_KaiJuZhenLiaoBaoGaoCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生时才能创建诊疗报告");
        }

        // 条件检查 end

        apply(
                new WenZhen_KaiJuZhenLiaoBaoGaoEvt(
                        cmd.getId(),
                        UUID.randomUUID(),
                        cmd.getZhengWen(),
                        cmd.getZhangHaoId(),
                        OffsetDateTime.now()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_KaiJuZhenLiaoBaoGaoEvt evt) {
        ZhenLiaoBaoGao record = new ZhenLiaoBaoGao();
        record.setId(evt.getZhenLiaoBaoGaoId());
        record.setZhengWen(evt.getZhengWen());
        record.setZhangHaoId(evt.getZhangHaoId());
        record.setShiJian(evt.getShiJian());

        this.zhenLiaoBaoGaoList.add(record);
    }

    @CommandHandler
    public void on(WenZhen_KaiJuChuFangCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生时才能开具处方");
        }

        // 条件检查 end

        apply(
                new WenZhen_KaiJuChuFangEvt(
                        cmd.getId(),
                        cmd.getZhengWen(),
                        cmd.getZhangHaoId(),
                        OffsetDateTime.now()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_KaiJuChuFangEvt evt) {
       ChuFang record = new ChuFang();
       record.setZhengWen(evt.getZhengWen());
       record.setKaiJuZhangHaoId(evt.getZhangHaoId());
       record.setKaiJuShiJian(evt.getShiJian());

       record.setZhuangTai(ChuFangZhuangTai.YI_KAI_JU);

       this.chuFang = record;
    }

    @CommandHandler
    public void on(WenZhen_QueRenChuFangCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生时才能确认处方");
        }

        // 处方状态
        if (chuFang.getZhuangTai() != ChuFangZhuangTai.YI_KAI_JU) {
            throw new PPBusinessException("只有在已开具处方时才能确认处方");
        }

        // 条件检查 end

        apply(
                new WenZhen_QueRenChuFangEvt(
                        cmd.getId(),
                        cmd.getZhangHaoId(),
                        OffsetDateTime.now()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_QueRenChuFangEvt evt) {
        this.chuFang.setQueRenZhangHaoId(evt.getZhangHaoId());
        this.chuFang.setQueRenShiJian(evt.getShiJian());

        this.chuFang.setZhuangTai(ChuFangZhuangTai.YI_QUE_REN);
    }

    @CommandHandler
    public void on(WenZhen_QuXiaoChuFangCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_AN_PAI_YI_SHENG) {
            throw new PPBusinessException("只有在已安排医生时才能取消处方");
        }

        // 处方状态
        if (!(chuFang != null && chuFang.getZhuangTai() != ChuFangZhuangTai.YI_QU_XIAO)) {
            throw new PPBusinessException("只有在未取消处方时才能取消处方");
        }

        // 条件检查 end

        apply(
                new WenZhen_QuXiaoChuFangEvt(
                        cmd.getId(),
                        cmd.getZhangHaoId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_QuXiaoChuFangEvt evt) {
        this.chuFang.setQuXiaoZhangHaoId(evt.getZhangHaoId());

        this.chuFang.setZhuangTai(ChuFangZhuangTai.YI_QU_XIAO);
    }
}