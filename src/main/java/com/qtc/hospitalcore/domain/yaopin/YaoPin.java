package com.qtc.hospitalcore.domain.yaopin;

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
import java.util.Map;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class YaoPin extends PPAggregate {
    public static enum ZhuangTai {
        ZAI_SHOU,
        TING_SHOU
    }

    @AggregateIdentifier
    UUID id;

    ZhuangTai zhuangTai;

    String mingCheng;
    String daLeiXing;
    String xiaoLeiXing;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;

    @CommandHandler
    public YaoPin(YaoPin_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new YaoPin_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getMingCheng(),
                        cmd.getDaLeiXing(),
                        cmd.getXiaoLeiXing(),
                        cmd.getXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YaoPin_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.mingCheng = evt.getMingCheng();
        this.daLeiXing = evt.getDaLeiXing();
        this.xiaoLeiXing = evt.getXiaoLeiXing();
        this.xinXiMap = evt.getXinXiMap();

        this.zhuangTai = ZhuangTai.TING_SHOU;
    }

    @CommandHandler
    public void on(YaoPin_GengXinCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new YaoPin_GengXinEvt(
                        cmd.getId(),
                        cmd.getMingCheng(),
                        cmd.getDaLeiXing(),
                        cmd.getXiaoLeiXing(),
                        cmd.getXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YaoPin_GengXinEvt evt) {
        this.mingCheng = evt.getMingCheng();
        this.daLeiXing = evt.getDaLeiXing();
        this.xiaoLeiXing = evt.getXiaoLeiXing();
        this.xinXiMap = evt.getXinXiMap();
    }

    @CommandHandler
    public void on(YaoPin_ShangJiaCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.TING_SHOU) {
            throw new PPBusinessException("不在停售状态, 不能上架");
        }

        // 条件检查 end

        apply(
                new YaoPin_ShangJiaEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YaoPin_ShangJiaEvt evt) {
        this.zhuangTai = ZhuangTai.ZAI_SHOU;
    }

    @CommandHandler
    public void on(YaoPin_XiaJiaCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.ZAI_SHOU) {
            throw new PPBusinessException("不在在售状态, 不能下架");
        }

        // 条件检查 end

        apply(
                new YaoPin_XiaJiaEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YaoPin_XiaJiaEvt evt) {
        this.zhuangTai = ZhuangTai.TING_SHOU;
    }

    @CommandHandler
    public void on(YaoPin_ShanChuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new YaoPin_ShanChuEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YaoPin_ShanChuEvt evt) {
        delete();
    }
}
