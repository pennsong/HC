package com.qtc.hospitalcore.domain.zhanghao;

import com.qtc.hospitalcore.domain.util.PPUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZhangHaoEventListener {

    ZhangHaoViewRepository repository;

    @Autowired
    public ZhangHaoEventListener(ZhangHaoViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ZhangHao_ChuangJianEvt evt) {
        ZhangHaoView record = new ZhangHaoView();
        record.setId(evt.getId());
        record.setUsername(evt.getUsername());
        record.setPassword(evt.getPassword());
        record.setJueSeSet(new HashSet<>(Arrays.asList(evt.getJueSe())));
        record.setYongHuId(evt.getYongHuId());
        record.setYiHuRenYuanId(evt.getYiHuRenYuanId());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ZhangHao_SheZhiMiMaEvt evt) {
        ZhangHaoView record = repository.findById(evt.getId()).get();
        record.setPassword(evt.getPassword());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ZhangHao_ShanChuEvt evt) {
        ZhangHaoView record = repository.findById(evt.getId()).get();
        record.delete();

        repository.saveAndFlush(record);
    }
}
