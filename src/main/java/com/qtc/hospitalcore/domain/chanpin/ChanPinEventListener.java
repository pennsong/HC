package com.qtc.hospitalcore.domain.chanpin;

import com.qtc.hospitalcore.domain.zhanghao.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChanPinEventListener {

    ChanPinViewRepository repository;

    @Autowired
    public ChanPinEventListener(ChanPinViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ChanPin_ChuangJianEvt evt) {
        ChanPinView record = new ChanPinView();
        record.setId(evt.getId());
        record.setMingCheng(evt.getMingCheng());
        record.setDaLeiXing(evt.getDaLeiXing());
        record.setXiaoLeiXing(evt.getXiaoLeiXing());
        record.setYuFuFei(evt.getYuFuFei());
        record.setShiChangJia(evt.getShiChangJia());
        record.setXinXiMap(evt.getXinXiMap());

        record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ChanPin_GengXinEvt evt) {
        ChanPinView record = repository.findById(evt.getId()).get();
        record.setMingCheng(evt.getMingCheng());
        record.setDaLeiXing(evt.getDaLeiXing());
        record.setXiaoLeiXing(evt.getXiaoLeiXing());
        record.setYuFuFei(evt.getYuFuFei());
        record.setShiChangJia(evt.getShiChangJia());
        record.setXinXiMap(evt.getXinXiMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ChanPin_ShangJiaEvt evt) {
        ChanPinView record = repository.findById(evt.getId()).get();
        record.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ChanPin_XiaJiaEvt evt) {
        ChanPinView record = repository.findById(evt.getId()).get();
        record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ChanPin_ShanChuEvt evt) {
        ChanPinView record = repository.findById(evt.getId()).get();
        record.delete();

        repository.saveAndFlush(record);
    }
}
