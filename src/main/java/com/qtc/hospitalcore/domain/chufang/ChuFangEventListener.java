package com.qtc.hospitalcore.domain.chufang;

import com.qtc.hospitalcore.domain.chanpin.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuFangEventListener {

    ChuFangViewRepository repository;

    @Autowired
    public ChuFangEventListener(ChuFangViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ChuFang_KaiJuEvt evt) {
        ChuFangView record = new ChuFangView();
        record.setId(evt.getId());
        record.setWenZhenId(evt.getWenZhenId());
        record.setZhengWen(evt.getZhengWen());
        record.setKaiJuZhangHaoId(evt.getKaiJuZhangHaoId());
        record.setKaiJuShiJian(evt.getKaiJuShiJian());

        record.setZhuangTai(ChuFang.ZhuangTai.YI_KAI_JU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ChuFang_QueRenEvt evt) {
        ChuFangView record = repository.findById(evt.getId()).get();

        record.setQueRenZhangHaoId(evt.getQueRenZhangHaoId());
        record.setQueRenShiJian(evt.getQueRenShiJian());

        record.setZhuangTai(ChuFang.ZhuangTai.YI_QUE_REN);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(ChuFang_QuXiaoEvt evt) {
        ChuFangView record = repository.findById(evt.getId()).get();

        record.setQuXiaoZhangHaoId(evt.getQuXiaoZhangHaoId());

        record.setZhuangTai(ChuFang.ZhuangTai.YI_QU_XIAO);

        repository.saveAndFlush(record);
    }
}
