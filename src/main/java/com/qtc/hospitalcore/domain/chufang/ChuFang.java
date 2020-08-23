package com.qtc.hospitalcore.domain.chufang;

import com.qtc.hospitalcore.domain.PPAggregate;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
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

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ChuFang extends PPAggregate {
    public static enum ZhuangTai {
        YI_KAI_JU,
        YI_QUE_REN,
        YI_QU_XIAO
    }

    @AggregateIdentifier
    UUID id;
    UUID wenZhenId;

    ZhuangTai zhuangTai;
    String zhengWen;

    UUID kaiJuZhangHaoId;
    OffsetDateTime kaiJuShiJian;

    UUID queRenZhangHaoId;
    OffsetDateTime queRenShiJian;

    UUID quXiaoZhangHaoId;

    @CommandHandler
    public ChuFang(ChuFang_KaiJuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new ChuFang_KaiJuEvt(
                        cmd.getId(),
                        cmd.getWenZhenId(),
                        cmd.getZhengWen(),
                        cmd.getKaiJuZhangHaoId(),
                        OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChuFang_KaiJuEvt evt) {
        this.id = evt.getId();
        this.wenZhenId = evt.getWenZhenId();
        this.kaiJuZhangHaoId = evt.getKaiJuZhangHaoId();
        this.zhengWen = evt.getZhengWen();
        this.kaiJuShiJian = evt.getKaiJuShiJian();

        this.zhuangTai = ZhuangTai.YI_KAI_JU;
    }

    @CommandHandler
    public void on(ChuFang_QueRenCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        if (this.zhuangTai != ZhuangTai.YI_KAI_JU) {
            throw new PPBusinessException("处方不在已开具状态, 不能做确认操作");
        }
        // 条件检查 end

        apply(
                new ChuFang_QueRenEvt(
                        cmd.getId(),
                        cmd.getQueRenZhangHaoId(),
                        OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChuFang_QueRenEvt evt) {
        this.queRenZhangHaoId = evt.getQueRenZhangHaoId();
        this.queRenShiJian = evt.getQueRenShiJian();

        this.zhuangTai = ZhuangTai.YI_QUE_REN;
    }

    @CommandHandler
    public void on(ChuFang_QuXiaoCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new ChuFang_QuXiaoEvt(
                        cmd.getId(),
                        cmd.getQuXiaoZhangHaoId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(ChuFang_QuXiaoEvt evt) {
        this.quXiaoZhangHaoId = evt.getQuXiaoZhangHaoId();

        this.zhuangTai = ZhuangTai.YI_QU_XIAO;
    }
}
