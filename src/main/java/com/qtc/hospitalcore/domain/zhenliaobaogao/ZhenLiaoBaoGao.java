package com.qtc.hospitalcore.domain.zhenliaobaogao;

import com.qtc.hospitalcore.domain.PPAggregate;
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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ZhenLiaoBaoGao extends PPAggregate {
    @AggregateIdentifier
    UUID id;
    UUID wenZhenId;

    String zhengWen;

    UUID kaiJuZhangHaoId;
    LocalDateTime kaiJuShiJian;

    @CommandHandler
    public ZhenLiaoBaoGao(ZhenLiaoBaoGao_KaiJuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new ZhenLiaoBaoGao_KaiJuEvt(
                        cmd.getId(),
                        cmd.getWenZhenId(),
                        cmd.getZhengWen(),
                        cmd.getKaiJuZhangHaoId(),
                        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ZhenLiaoBaoGao_KaiJuEvt evt) {
        this.id = evt.getId();
        this.wenZhenId = evt.getWenZhenId();
        this.kaiJuZhangHaoId = evt.getKaiJuZhangHaoId();
        this.zhengWen = evt.getZhengWen();
        this.kaiJuShiJian = evt.getKaiJuShiJian();
    }
}
