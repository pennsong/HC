package com.qtc.hospitalcore.domain.yihurenyuan;

import com.qtc.hospitalcore.domain.PPAggregate;
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

    @AggregateIdentifier
    UUID id;

    String xingMing;

    String shenFenZheng;

    Set<QuanXian> quanXianSet;

    @Convert(converter = HashMapConverter.class)
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
                cmd.getShenFenZheng(),
                cmd.getQuanXianSet(),
                cmd.getXinXiMap()
        ));
    }

    @EventSourcingHandler
    public void on(YiHuRenYuan_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.xingMing = evt.getXingMing();
        this.shenFenZheng = evt.getShenFenZheng();
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
                cmd.getShenFenZheng(),
                cmd.getXinXiMap()
        ));
    }

    @EventSourcingHandler
    public void on(YiHuRenYuan_GengXinEvt evt) {
        this.xingMing = evt.getXingMing();
        this.shenFenZheng = evt.getShenFenZheng();
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
