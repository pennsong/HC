package com.qtc.hospitalcore.domain.yonghu;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import com.sun.scenario.effect.Offset;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.annotations.Type;
import org.hibernate.type.UUIDCharType;

import javax.persistence.Convert;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class YongHu {

    @AggregateIdentifier
    UUID id;

    String shouJiHao;
    String xingMing;
    String shenFenZheng;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;

    @CommandHandler
    public YongHu(ChuangJianYongHuCmd cmd, MetaData metaData) {
        // 参数检查

        // 条件检查

        apply(
                new ChuangJianYongHuEvt(
                        cmd.getYongHuId(),
                        cmd.getShouJiHaoMa()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChuangJianYongHuEvt evt) {
        this.id = evt.getYongHuId();
        this.shouJiHao = evt.getShouJiHaoMa();
    }

    @CommandHandler
    public void on(DiJiaoJiBenXinXiCmd cmd, MetaData metaData) {
        // 参数检查

        // 条件检查

        apply(
                new DiJiaoJiBenXinXiEvt(
                        cmd.getYongHuId(),
                        cmd.getXingMing(),
                        cmd.getShenFenZheng(),
                        cmd.getJiBenXinXiNeiRong()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(DiJiaoJiBenXinXiEvt evt) {
        this.xingMing = evt.getXingMing();
        this.shenFenZheng = evt.getShenFenZheng();
        this.xinXi = evt.getJiBenXinXiNeiRong();
    }
}
