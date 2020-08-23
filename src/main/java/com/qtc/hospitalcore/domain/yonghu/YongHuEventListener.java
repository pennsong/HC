package com.qtc.hospitalcore.domain.yonghu;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YongHuEventListener {

    YongHuViewRepository repository;

    @Autowired
    public YongHuEventListener(YongHuViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(YongHu_ChuangJianEvt evt) {
        YongHuView record = new YongHuView();
        record.setId(evt.getId());
        record.setShouJiHao(evt.getShouJiHao());
        record.setWeiXinOpenId(evt.getWeiXinOpenId());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YongHu_ChuangJianJiBenXinXiEvt evt) {
        YongHuView record = repository.findById(evt.getId()).get();

        record.setXingMing(evt.getXingMing());
        record.setShenFenZheng(evt.getShenFenZheng());
        record.setXinXiMap(evt.getXinXiMap());

        repository.saveAndFlush(record);
    }
    
}
