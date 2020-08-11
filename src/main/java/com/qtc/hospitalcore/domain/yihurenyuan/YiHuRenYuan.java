package com.qtc.hospitalcore.domain.yihurenyuan;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
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
public class YiHuRenYuan {
    public static enum QuanXian {
        WEN_ZHEN,
        KAI_JU_CHU_FANG,
        QUE_REN_CHU_FANG,
        BIAN_JI_BING_LI
    }

    @AggregateIdentifier
    UUID id;

    String dengluMing;

    String dengLuMiMa;

    String xingMing;

    Set<QuanXian> quanXianSet;

    Map<String, Object> xinXiMap;

    @CommandHandler
    public YiHuRenYuan(YiHuRenYuan_ChuangJianCmd cmd) {
        apply(new YiHuRenYuan_ChuangJianEvt(
                cmd.getId(),
                cmd.getDengluMing(),
                cmd.getDengLuMiMa(),
                cmd.getXingMing(),
                cmd.getQuanXianSet(),
                cmd.getXinXiMap()
        ));
    }

    @EventSourcingHandler
    public void on(YiHuRenYuan_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.dengluMing = evt.getDengluMing();
        this.dengLuMiMa = evt.getDengLuMiMa();
        this.xingMing = evt.getXingMing();
        this.quanXianSet = evt.getQuanXianSet();
        this.xinXiMap = evt.getXinXiMap();
    }

}
