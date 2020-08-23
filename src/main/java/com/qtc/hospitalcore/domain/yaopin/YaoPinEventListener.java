package com.qtc.hospitalcore.domain.yaopin;

import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YaoPinEventListener {

    YaoPinViewRepository repository;

    @Autowired
    public YaoPinEventListener(YaoPinViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(YaoPin_ChuangJianCmd evt) {
        YaoPinView record = new YaoPinView();
        record.setId(evt.getId());
        record.setMingCheng(evt.getMingCheng());
        record.setDaLeiXing(evt.getDaLeiXing());
        record.setXiaoLeiXing(evt.getXiaoLeiXing());
        record.setXinXi(evt.getXinXiMap());

        record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YaoPin_GengXinEvt evt) {
        YaoPinView record = repository.findById(evt.getId()).get();

        record.setMingCheng(evt.getMingCheng());
        record.setDaLeiXing(evt.getDaLeiXing());
        record.setXiaoLeiXing(evt.getXiaoLeiXing());
        record.setXinXi(evt.getXinXiMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YaoPin_ShangJiaEvt evt) {
        YaoPinView record = repository.findById(evt.getId()).get();

        record.setZhuangTai(YaoPin.ZhuangTai.ZAI_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YaoPin_XiaJiaEvt evt) {
        YaoPinView record = repository.findById(evt.getId()).get();

        record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(YaoPin_ShanChuEvt evt) {
        YaoPinView record = repository.findById(evt.getId()).get();

        record.delete();

        repository.saveAndFlush(record);
    }
}
