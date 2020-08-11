package com.qtc.hospitalcore.domain.yonghu;

import com.qtc.hospitalcore.domain.query.YongHuViewRepository;
import com.qtc.hospitalcore.domain.yonghu.ChuangJianYongHuEvt;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;


public class YongHuProjector {
    private final YongHuViewRepository repository;

    public YongHuProjector(YongHuViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ChuangJianYongHuEvt evt) {
        YongHuView entity = new YongHuView(
                evt.getYongHuId(),
                evt.getShouJiHaoMa(),
                null,
                null,
                null
        );

        repository.save(entity);
    }
}
