package com.qtc.hospitalcore.domain.yonghu;

import com.qtc.hospitalcore.domain.query.YongHuViewRepository;
import org.axonframework.eventhandling.EventHandler;


public class YongHuProjector {
    private final YongHuViewRepository repository;

    public YongHuProjector(YongHuViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(YongHu_ChuangJianEvt evt) {
        YongHuView entity = new YongHuView(
                evt.getYongHuId(),
                evt.getShouJiHaoMa(),
                evt.getWeiXinOpenId(),
                null,
                null,
                null
        );

        repository.save(entity);
    }
}
