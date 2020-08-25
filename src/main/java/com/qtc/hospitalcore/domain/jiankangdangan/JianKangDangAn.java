package com.qtc.hospitalcore.domain.jiankangdangan;

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
public class JianKangDangAn extends PPAggregate {

    @AggregateIdentifier
    UUID id;

    UUID yongHuId;

    ZhuangTai zhuangTai;

    String xingMing;
    String shenFenZhengHao;
    String shouJiHao;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jiBenXinXiMap;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jianKangXinXiMap;


    @CommandHandler
    public JianKangDangAn(JianKangDangAn_ChuangJianCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new JianKangDangAn_ChuangJianEvt(
                        cmd.getId(),
                        cmd.getYongHuId(),
                        cmd.getXingMing(),
                        cmd.getShenFenZhengHao(),
                        cmd.getShouJiHao(),
                        cmd.getJiBenXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(JianKangDangAn_ChuangJianEvt evt) {
        this.id = evt.getId();
        this.xingMing = evt.getXingMing();
        this.shenFenZhengHao = evt.getShenFenZhengHao();
        this.shouJiHao = evt.getShouJiHao();
        this.jiBenXinXiMap = evt.getJiBenXinXiMap();

        this.zhuangTai = ZhuangTai.YI_CHUANG_JIAN;
    }

    @CommandHandler
    public void on(JianKangDangAn_GengXinJianKangXinXiCmd cmd, MetaData metaData) {
        // 条件检查
        // 删除检查
        checkDeleted();

        // 条件检查 end

        apply(
                new JianKangDangAn_GengXinJianKangXinXiEvt(
                        cmd.getId(),
                        cmd.getJianKangXinXiMap()
                ),
                metaData
        );
    }

    @EventSourcingHandler
    public void on(JianKangDangAn_GengXinJianKangXinXiEvt evt) {
        this.jianKangXinXiMap = evt.getJianKangXinXiMap();

        this.zhuangTai = ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN;
    }
}
