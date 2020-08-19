package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.PPAggregate;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ChuangJianCmd;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ChuangJianEvt;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Convert;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public static enum JieGuo {
        CHENG_GONG,
        JIE_SHU
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class YuFuKuan {
        String liuShuiHao;
        LocalDateTime shiJian;
        BigDecimal jinE;
        String beiZhu;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class BuChongKuan {
        String liuShuiHao;
        LocalDateTime shiJian;
        String fuKuanFang;
        String biZhong;
        BigDecimal jinE;
        double fuKuanDangRiHuiLv;
        String beiZhu;
        List<String> pingZheng;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TuiKuan {
        String liuShuiHao;
        LocalDateTime shiJian;
        String shouKuanZhangHuMing;
        String shouKuanZhangHu;
        BigDecimal jinE;
        String beiZhu;
        List<String> pingZheng;
    }

    public static enum ZhuangTai {
        YI_CHUANG_JIAN,
        YI_AN_PAI_YI_SHENG,
        YI_WAN_CHENG
    }

    public static enum FuFeiZhuangTai {
        YI_YU_FU_FEI_ZHI_FU,
        YI_QUAN_KUAN_ZHI_FU
    }

    public static enum HuiZhenZhuangTai {
        YI_AN_PAI
    }

    @AggregateIdentifier
    UUID id;

    ZhuangTai zhuangTai;
    FuFeiZhuangTai fuFeiZhuangTai;
    HuiZhenZhuangTai huiZhenZhuangTai;

    LocalDateTime xiaDanShiJian;
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
                        // TODO: PP 正式发布时去掉truncatedTo(ChronoUnit.MINUTES), 这里是为了测试方便
                        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
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
    }

    @CommandHandler
    public void on(WenZhen_ZhiFuYuFuKuanCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_CHUANG_JIAN) {
            throw new PPBusinessException("只有在已创建状态才能接收预付款");
        }

        // 金额
        if (cmd.getJinE().doubleValue() < yuFuFei.doubleValue()) {
            throw new PPBusinessException("预付款不足");
        }

        if (cmd.getJinE().doubleValue() > zongJia.doubleValue()) {
            throw new PPBusinessException("预付款不能超过总价");
        }

        // 条件检查 end

        apply(
                new WenZhen_ZhiFuYuFuKuanEvt(
                        cmd.getId(),
                        cmd.getLiuShuiHao(),
                        cmd.getBeiZhu(),
                        cmd.getJinE(),
                        // TODO: PP 正式发布时去掉truncatedTo(ChronoUnit.MINUTES), 这里是为了测试方便
                        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
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

        fuFeiZhuangTai = FuFeiZhuangTai.YI_YU_FU_FEI_ZHI_FU;
    }

    @CommandHandler
    public void on(WenZhen_ZhiXingTuiKuanCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai != ZhuangTai.YI_CHUANG_JIAN) {
            throw new PPBusinessException("只有在已创建状态才能接收预付款");
        }

        // 金额
        // balance
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

        if (balance < cmd.getJinE().doubleValue()) {
            throw new PPBusinessException("退款额度太大");
        }
        // 金额 end

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
                        cmd.getPingZheng()
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
                evt.getPingZheng()
        );

        this.tuiKuanList.add(tuiKuan);
    }

    @CommandHandler
    public void on(WenZhen_JianKangDangAnGengXinCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 状态
        if (zhuangTai == ZhuangTai.YI_WAN_CHENG) {
            throw new PPBusinessException("只有在非已完成状态才能更新健康档案");
        }

        // 付费状态
        if (fuFeiZhuangTai != FuFeiZhuangTai.YI_YU_FU_FEI_ZHI_FU) {
            throw new PPBusinessException("只有在已预付费支付状态才能更新健康档案");
        }

        // 条件检查 end

        apply(
                new WenZhen_JianKangDangAnGengXinEvt(
                        cmd.getId(),
                        cmd.getJianKangDangAnMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(WenZhen_JianKangDangAnGengXinEvt evt) {
       this.jianKangDangAnMap = evt.getJianKangDangAnMap();
    }
}