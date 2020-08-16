package com.qtc.hospitalcore.domain.zhanghao;

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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ZhangHao {
    public static enum JueSe {
        YONG_HU,
        YI_HU_REN_YUAN
    }

    @AggregateIdentifier
    UUID id;
    String username;
    String password;
    Set<JueSe> jueSeSet;

    UUID yongHuId;

    UUID yiHuRenYuanId;

    @CommandHandler
    public ZhangHao(ZhangHao_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查

        // 条件检查 end

        apply(
                new ZhangHao_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getUsername(),
                        cmd.getPassword(),
                        cmd.getJueSe(),
                        cmd.getYongHuId(),
                        cmd.getYiHuRenYuanId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ZhangHao_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.username = evt.getUsername();
        this.password = evt.getPassword();
        Set<JueSe> set = new HashSet<>();
        set.add(evt.getJueSe());
        this.jueSeSet = set;
        this.yongHuId = evt.getYongHuId();
        this.yiHuRenYuanId = evt.getYiHuRenYuanId();
    }

    @CommandHandler
    public void on(ZhangHao_SheZhiMiMaCmd cmd, MetaData metaData) {
        // 条件检查

        // 条件检查 end

        apply(
                new ZhangHao_SheZhiMiMaEvt(
                        cmd.getId(),
                        cmd.getPassword()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ZhangHao_SheZhiMiMaEvt evt) {
        this.password = evt.getPassword();
    }

    @CommandHandler
    public void on(ZhangHao_ShanChuCmd cmd, MetaData metaData) {
        // 条件检查

        // 条件检查 end

        apply(
                new ZhangHao_ShanChuEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ZhangHao_ShanChuEvt evt) {
       markDeleted();
    }
}
