package com.qtc.hospitalcore.domain.yonghu;

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
public class YongHu {

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

        // 条件检查 end

        apply(
                new YongHu_ChuangJianEvt(
                        cmd.getYongHuId(),
                        cmd.getShouJiHaoMa(),
                        cmd.getWeiXinOpenId()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YongHu_ChuangJianEvt evt) {
        this.id = evt.getYongHuId();
        this.shouJiHao = evt.getShouJiHaoMa();
        this.weiXinOpenId = evt.getWeiXinOpenId();
    }

    @CommandHandler
    public void on(YongHu_ChuangJianJiBenXinXiCmd cmd, MetaData metaData) {
        // 条件检查

        // 条件检查 end

        apply(
                new YongHu_ChuangJianJiBenXinXiEvt(
                        cmd.getYongHuId(),
                        cmd.getXingMing(),
                        cmd.getShenFenZheng(),
                        cmd.getJiBenXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(YongHu_ChuangJianJiBenXinXiEvt evt) {
        this.xingMing = evt.getXingMing();
        this.shenFenZheng = evt.getShenFenZheng();
        this.xinXiMap = evt.getJiBenXinXiMap();
    }
}
