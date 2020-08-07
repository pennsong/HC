package com.qtc.hospitalcore.domain.query;

import com.qtc.hospitalcore.domain.YiHuRenYuan_ChuangJianEvt;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class YiHuRenYuanProjector {
    private final YiHuRenYuanViewRepository repository;

    public YiHuRenYuanProjector(YiHuRenYuanViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(YiHuRenYuan_ChuangJianEvt evt) {
        YiHuRenYuanView entity = new YiHuRenYuanView(
                evt.getId(),
                evt.getQuanXianSet(),
                evt.getXinXiMap()
        );

        repository.save(entity);
    }
}
