package com.qtc.hospitalcore.domain.paiban;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaiBanEventListener {

    PaiBanViewRepository repository;

    @Autowired
    public PaiBanEventListener(PaiBanViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(PaiBan_ChuangJianEvt evt) {
        PaiBanView record = new PaiBanView();
        record.setId(evt.getId());
        record.setChanPinId(evt.getChanPinId());
        record.setYuFuFei(evt.getYuFuFei());
        record.setShiChangJia(evt.getShiChangJia());
        record.setYiSheng(evt.getYiSheng());
        record.setShiJian(evt.getShiJian());
        record.setXinXiMap(evt.getXinXiMap());

        record.setShouChu(false);
        record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(PaiBan_ShangJiaEvt evt) {
        PaiBanView record = repository.findById(evt.getId()).get();

        record.setZhuangTai(PaiBan.ZhuangTai.ZAI_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(PaiBan_XiaJiaEvt evt) {
        PaiBanView record = repository.findById(evt.getId()).get();

        record.setZhuangTai(PaiBan.ZhuangTai.TING_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(PaiBan_ShouChuEvt evt) {
        PaiBanView record = repository.findById(evt.getId()).get();

        record.setShouChu(true);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(PaiBan_ShanChuEvt evt) {
        PaiBanView record = repository.findById(evt.getId()).get();

        record.delete();

        repository.saveAndFlush(record);
    }
}
