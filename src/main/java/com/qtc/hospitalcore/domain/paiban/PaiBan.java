package com.qtc.hospitalcore.domain.paiban;

import com.qtc.hospitalcore.domain.PPAggregate;
import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
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
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PaiBan extends PPAggregate {
    public static enum ZhuangTai {
        ZAI_SHOU,
        TING_SHOU
    }

    @AggregateIdentifier
    UUID id;

    ZhuangTai zhuangTai;

    UUID chanPinId;

    BigDecimal yuFuFei;
    BigDecimal shiChangJia;

    String yiSheng;

    // 到小时
    OffsetDateTime shiJian;

    // 售出
    boolean shouChu;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;

    @CommandHandler
    public PaiBan(PaiBan_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new PaiBan_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getChanPinId(),
                        cmd.getYuFuFei(),
                        cmd.getShiChangJia(),
                        cmd.getYiSheng(),
                        cmd.getShiJian(),
                        cmd.getXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(PaiBan_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.chanPinId = evt.getChanPinId();
        this.yuFuFei = evt.getYuFuFei();
        this.shiChangJia = evt.getShiChangJia();
        this.yiSheng = evt.getYiSheng();
        this.shiJian = evt.getShiJian();
        this.xinXiMap = evt.getXinXiMap();

        this.shouChu = false;
        this.zhuangTai = ZhuangTai.TING_SHOU;
    }

    @CommandHandler
    public void on(PaiBan_ShangJiaCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.TING_SHOU) {
            throw new PPBusinessException("不在停售状态, 不能上架");
        }

        // 条件检查 end

        apply(
                new PaiBan_ShangJiaEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(PaiBan_ShangJiaEvt evt) {
        this.zhuangTai = ZhuangTai.ZAI_SHOU;
    }

    @CommandHandler
    public void on(PaiBan_XiaJiaCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.ZAI_SHOU) {
            throw new PPBusinessException("不在在售状态, 不能下架");
        }

        // 条件检查 end

        apply(
                new PaiBan_XiaJiaEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(PaiBan_XiaJiaEvt evt) {
        this.zhuangTai = ZhuangTai.TING_SHOU;
    }

    @CommandHandler
    public void on(PaiBan_ShouChuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 在售检查
        if (this.zhuangTai != ZhuangTai.ZAI_SHOU) {
            throw new PPBusinessException("非在售状态, 不能售出");
        }

        // 售出检查
        if (this.shouChu) {
            throw new PPBusinessException("已售出, 不能再次售出");
        }
        // 条件检查 end

        apply(
                new PaiBan_ShouChuEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(PaiBan_ShouChuEvt evt) {
        this.shouChu = true;
    }

    @CommandHandler
    public void on(PaiBan_ShanChuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new PaiBan_ShanChuEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(PaiBan_ShanChuEvt evt) {
        delete();
    }
}
