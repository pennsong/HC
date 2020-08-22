package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.paiban.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WenZhenEventListener {

    WenZhenViewRepository repository;

    @Autowired
    public WenZhenEventListener(WenZhenViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(WenZhen_ChuangJianEvt evt) {
        WenZhenView record = new WenZhenView();

        record.setId(evt.getId());
        record.setJianKangDangAnId(evt.getJianKangDangAnId());
        record.setChanPinId(evt.getChanPinId());
        record.setPaiBanId(evt.getPaiBanId());
        record.setYuFuFei(evt.getYuFuFei());
        record.setZongJia(evt.getZongJia());
        record.setChanPinMingCheng(evt.getChanPinMingCheng());

        record.setChanPinJsonString(evt.getChanPinJsonString());
        record.setPaiBanJsonString(evt.getPaiBanJsonString());

        record.setJianKangDangAnMap(evt.getJianKangDangAnMap());

        record.setXiaDanShiJian(evt.getXiaDanShiJian());

        record.setZhuangTai(WenZhen.ZhuangTai.YI_CHUANG_JIAN);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhiFuYuFuKuanEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        WenZhen.YuFuKuan yuFuKuan = new WenZhen.YuFuKuan();
        yuFuKuan.setLiuShuiHao(evt.getLiuShuiHao());
        yuFuKuan.setBeiZhu(evt.getBeiZhu());
        yuFuKuan.setJinE(evt.getJinE());
        yuFuKuan.setShiJian(evt.getShiJian());

        record.setYuFuKuan(yuFuKuan);

        record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_YU_FU_FEI);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhiXingTuiKuanEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        WenZhen.TuiKuan tuiKuan = new WenZhen.TuiKuan();

        tuiKuan.setLiuShuiHao(evt.getLiuShuiHao());
        tuiKuan.setShiJian(evt.getShiJian());
        tuiKuan.setShouKuanZhangHuMing(evt.getShouKuanZhangHuMing());
        tuiKuan.setShouKuanZhangHu(evt.getShouKuanZhangHu());
        tuiKuan.setJinE(evt.getJinE());
        tuiKuan.setBeiZhu(evt.getBeiZhu());
        tuiKuan.setPingZheng(evt.getPingZheng());

        record.getTuiKuanList().add(tuiKuan);

        repository.saveAndFlush(record);
    }
}
