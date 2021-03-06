package com.qtc.hospitalcore.domain.yihurenyuan;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YiHuRenYuanEventListener {

    YiHuRenYuanViewRepository repository;

    @Autowired
    public YiHuRenYuanEventListener(YiHuRenYuanViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(YiHuRenYuan_ChuangJianEvt evt) {
        YiHuRenYuanView record = new YiHuRenYuanView();
        record.setId(evt.getId());
        record.setXingMing(evt.getXingMing());
        record.setShenFenZheng(evt.getShenFenZheng());
        record.setQuanXianMap(evt.getQuanXianMap());
        record.setXinXiMap(evt.getXinXiMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YiHuRenYuan_GengXinEvt evt) {
        YiHuRenYuanView record = repository.findById(evt.getId()).get();

        record.setXingMing(evt.getXingMing());
        record.setShenFenZheng(evt.getShenFenZheng());
        record.setXinXiMap(evt.getXinXiMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YiHuRenYuan_SheZhiQuanXianEvt evt) {
        YiHuRenYuanView record = repository.findById(evt.getId()).get();

        record.setQuanXianMap(evt.getQuanXianMap());

        repository.saveAndFlush(record);
    }
}
