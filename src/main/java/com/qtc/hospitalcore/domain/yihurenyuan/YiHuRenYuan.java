package com.qtc.hospitalcore.domain.yihurenyuan;

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

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class YiHuRenYuan extends PPAggregate {
    public static enum QuanXian {
        WEN_ZHEN,
        KAI_JU_CHU_FANG,
        QUE_REN_CHU_FANG,
        BIAN_JI_BING_LI
    }

    @AggregateIdentifier
    UUID id;

    String xingMing;

    String shenFenZhengHao;

    Set<QuanXian> quanXianSet;

    Map<String, Object> xinXiMap;

    @CommandHandler
    public YiHuRenYuan(YiHuRenYuan_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
		// 删除检查
        checkDeleted();

        // 条件检查 end

        apply(new YiHuRenYuan_ChuangJianEvt(
                cmd.getId(),
                cmd.getXingMing(),
                cmd.getShenFenZhengHao(),
                cmd.getQuanXianSet(),
                cmd.getXinXiMap()
        ));
    }

    @EventSourcingHandler
    public void on(YiHuRenYuan_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.xingMing = evt.getXingMing();
        this.shenFenZhengHao = evt.getShenFenZhengHao();
        this.quanXianSet = evt.getQuanXianSet();
        this.xinXiMap = evt.getXinXiMap();
    }

    @CommandHandler
    public void on(YiHuRenYuan_GengXinCmd cmd, MetaData metaData) {
        // 条件检查
		// 删除检查
        checkDeleted();

        // 条件检查 end

        apply(new YiHuRenYuan_GengXinEvt(
                cmd.getId(),
                cmd.getXingMing(),
                cmd.getShenFenZhengHao(),
                cmd.getXinXiMap()
        ));
    }

    @EventSourcingHandler
    public void on(YiHuRenYuan_GengXinEvt evt) {
        this.xingMing = evt.getXingMing();
        this.shenFenZhengHao = evt.getShenFenZhengHao();
        this.xinXiMap = evt.getXinXiMap();
    }

    @CommandHandler
    public void on(YiHuRenYuan_SheZhiQuanXianCmd cmd, MetaData metaData) {
        // 条件检查
		// 删除检查
        checkDeleted();

        // 条件检查 end

        apply(new YiHuRenYuan_SheZhiQuanXianEvt(
                cmd.getId(),
                cmd.getQuanXianSet()
        ));
    }

    @EventSourcingHandler
    public void on(YiHuRenYuan_SheZhiQuanXianEvt evt) {
       this.quanXianSet = evt.getQuanXianSet();
    }
}
