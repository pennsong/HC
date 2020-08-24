package com.qtc.hospitalcore.domain.yonghu;

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
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class YongHu extends PPAggregate {

    @AggregateIdentifier
    UUID id;

    String shouJiHao;
    String xingMing;
    String shenFenZheng;
    String weiXinOpenId;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;

    @CommandHandler
    public YongHu(YongHu_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
		// 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new YongHu_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getShouJiHao(),
                        cmd.getWeiXinOpenId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YongHu_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.shouJiHao = evt.getShouJiHao();
        this.weiXinOpenId = evt.getWeiXinOpenId();
    }

    @CommandHandler
    public void on(YongHu_ChuangJianJiBenXinXiCmd cmd, MetaData metaData) {
        // 条件检查
		// 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new YongHu_ChuangJianJiBenXinXiEvt(
                        cmd.getId(),
                        cmd.getXingMing(),
                        cmd.getShenFenZheng(),
                        cmd.getXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YongHu_ChuangJianJiBenXinXiEvt evt) {
        this.xingMing = evt.getXingMing();
        this.shenFenZheng = evt.getShenFenZheng();
        this.xinXiMap = evt.getXinXiMap();
    }

    @CommandHandler
    public void on(YongHu_ShanChuCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new YongHu_ShanChuEvt(
                        cmd.getId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YongHu_ShanChuEvt evt) {
       this.delete();
    }
}
