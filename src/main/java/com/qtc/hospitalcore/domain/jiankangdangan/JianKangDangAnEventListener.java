package com.qtc.hospitalcore.domain.jiankangdangan;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JianKangDangAnEventListener {

    JianKangDangAnViewRepository repository;

    @Autowired
    public JianKangDangAnEventListener(JianKangDangAnViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(JianKangDangAn_ChuangJianEvt evt) {
        JianKangDangAnView record = new JianKangDangAnView();
        record.setId(evt.getId());
        record.setXingMing(evt.getXingMing());
        record.setShenFenZhengHao(evt.getShenFenZhengHao());
        record.setShouJiHao(evt.getShouJiHao());
        record.setJiBenXinXiMap(evt.getJiBenXinXiMap());

        record.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(JianKangDangAn_GengXinJianKangXinXiEvt evt) {
        JianKangDangAnView record = repository.findById(evt.getId()).get();

        record.setJianKangXinXiMap(evt.getJianKangXinXiMap());

        record.setZhuangTai(ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN);

        repository.saveAndFlush(record);
    }
}
