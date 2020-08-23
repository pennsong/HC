package com.qtc.hospitalcore.domain.wenzhen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.qtc.hospitalcore.domain.util.PPJsonType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        tuiKuan.setPingZhengList(Strings.join(evt.getPingZhengList(), ','));

        record.getTuiKuanList().add(tuiKuan);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinJianKangDangAnEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setJianKangDangAnMap(evt.getJianKangDangAnMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhiFuBuChongKuanEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        WenZhen.BuChongKuan buChongKuan = new WenZhen.BuChongKuan();
        buChongKuan.setLiuShuiHao(evt.getLiuShuiHao());
        buChongKuan.setShiJian(evt.getShiJian());
        buChongKuan.setFuKuanFang(evt.getFuKuanFang());
        buChongKuan.setBiZhong(evt.getBiZhong());
        buChongKuan.setJinE(evt.getJinE());
        buChongKuan.setFuKuanDangRiHuiLv(evt.getFuKuanDangRiHuiLv());
        buChongKuan.setBeiZhu(evt.getBeiZhu());
        buChongKuan.setPingZhengList(Strings.join(evt.getPingZhengList(), ','));

        record.getBuChongKuanList().add(buChongKuan);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhiFuQuanKuanEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setFuFeiZhuangTai(WenZhen.FuFeiZhuangTai.YI_ZHI_FU_QUAN_KUAN);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_AnPaiYiShengEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setWenZhenZhangHaoId(evt.getWenZhenZhangHaoId());
        record.setBingLiBianJiZhangHaoId(evt.getBingLiBianJiZhangHaoId());
        record.setKaiJuChuFangZhangHaoId(evt.getKaiJuChuFangZhangHaoId());
        record.setQueRenChuFangZhangHaoId(evt.getQueRenChuFangZhangHaoId());

        record.setZhuangTai(WenZhen.ZhuangTai.YI_AN_PAI_YI_SHENG);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhuanZhenEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setWenZhenZhangHaoId(evt.getWenZhenZhangHaoId());
        record.setBingLiBianJiZhangHaoId(evt.getBingLiBianJiZhangHaoId());
        record.setKaiJuChuFangZhangHaoId(evt.getKaiJuChuFangZhangHaoId());
        record.setQueRenChuFangZhangHaoId(evt.getQueRenChuFangZhangHaoId());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_AnPaiHuiZhenEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        WenZhen.HuiZhen huiZhen = new WenZhen.HuiZhen();
        huiZhen.setShiJian(evt.getShiJian());
        huiZhen.setLianJie(evt.getLianJie());
        huiZhen.setHuiYiId(evt.getHuiYiId());
        huiZhen.setHuanFangCanYuRenYuan(evt.getHuanFangCanYuRenYuan());
        huiZhen.setBeiZhu(evt.getBeiZhu());

        record.setHuiZhen(huiZhen);

        record.setHuiZhenZhuangTai(WenZhen.HuiZhenZhuangTai.YI_AN_PAI);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_SheZhiHuiZhenShiPinEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.getHuiZhen().setShiPinLianJie(evt.getShiPinLianJie());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinMuQianZhuYaoZhenDuanEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setMuQianZhuYaoZhenDuan(evt.getZhenDuan());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setXiangXiZhiLiaoJingGuoMap(evt.getXiangXiZhiLiaoJingGuoMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinJianChaZongJieEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setJianChaZongJieMap(evt.getJianChaZongJieMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinDianZiYingXiangEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setDianZiYingXiangMap(evt.getDianZiYingXiangMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinQiTaCaiLiaoEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setQiTaCaiLiaoMap(evt.getQiTaCaiLiaoMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ChengGongWanChengEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setWanChengBeiZhu(evt.getBeiZhu());
        record.setZhuangTai(WenZhen.ZhuangTai.YI_CHENG_GONG_WAN_CHENG);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhongDuanWanChengEvt evt) throws JsonProcessingException {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setWanChengBeiZhu(evt.getBeiZhu());
        record.setZhuangTai(WenZhen.ZhuangTai.YI_JIE_SHU_WAN_CHENG);

        repository.saveAndFlush(record);
    }
}
