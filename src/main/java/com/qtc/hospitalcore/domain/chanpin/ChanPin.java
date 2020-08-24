package com.qtc.hospitalcore.domain.chanpin;

import com.qtc.hospitalcore.domain.PPAggregate;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAn_GengXinJianKangXinXiCmd;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAn_GengXinJianKangXinXiEvt;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
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
import java.util.Map;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ChanPin extends PPAggregate {

    @AggregateIdentifier
    UUID id;

    ZhuangTai zhuangTai;

    String mingCheng;
    String daLeiXing;
    String xiaoLeiXing;

    BigDecimal yuFuFei;
    BigDecimal shiChangJia;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;

    @CommandHandler
    public ChanPin(ChanPin_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new ChanPin_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getMingCheng(),
                        cmd.getDaLeiXing(),
                        cmd.getXiaoLeiXing(),
                        cmd.getYuFuFei(),
                        cmd.getShiChangJia(),
                        cmd.getXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChanPin_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.mingCheng = evt.getMingCheng();
        this.daLeiXing = evt.getDaLeiXing();
        this.xiaoLeiXing = evt.getXiaoLeiXing();
        this.yuFuFei = evt.getYuFuFei();
        this.shiChangJia = evt.getShiChangJia();
        this.xinXiMap = evt.getXinXiMap();

        this.zhuangTai = ZhuangTai.TING_SHOU;
    }

    @CommandHandler
    public void on(ChanPin_GengXinCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new ChanPin_GengXinEvt(
                        cmd.getId(),
                        cmd.getMingCheng(),
                        cmd.getDaLeiXing(),
                        cmd.getXiaoLeiXing(),
                        cmd.getYuFuFei(),
                        cmd.getShiChangJia(),
                        cmd.getXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChanPin_GengXinEvt evt) {
        this.mingCheng = evt.getMingCheng();
        this.daLeiXing = evt.getDaLeiXing();
        this.xiaoLeiXing = evt.getXiaoLeiXing();
        this.yuFuFei = evt.getYuFuFei();
        this.shiChangJia = evt.getShiChangJia();
        this.xinXiMap = evt.getXinXiMap();
    }

    @CommandHandler
    public void on(ChanPin_ShangJiaCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.TING_SHOU) {
            throw new PPBusinessException("不在停售状态, 不能上架");
        }

        // 条件检查 end

        apply(
                new ChanPin_ShangJiaEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChanPin_ShangJiaEvt evt) {
       this.zhuangTai = ZhuangTai.ZAI_SHOU;
    }

    @CommandHandler
    public void on(ChanPin_XiaJiaCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.ZAI_SHOU) {
            throw new PPBusinessException("不在在售状态, 不能下架");
        }

        // 条件检查 end

        apply(
                new ChanPin_XiaJiaEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChanPin_XiaJiaEvt evt) {
        this.zhuangTai = ZhuangTai.TING_SHOU;
    }

    @CommandHandler
    public void on(ChanPin_ShanChuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new ChanPin_ShanChuEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChanPin_ShanChuEvt evt) {
       delete();
    }
}
