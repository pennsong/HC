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
    public void on(WenZhen_SheZhiHuiZhenShiPinEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.getHuiZhen().setShiPinLianJie(evt.getShiPinLianJie());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinMuQianZhuYaoZhenDuanEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setMuQianZhuYaoZhenDuan(evt.getZhenDuan());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setXiangXiZhiLiaoJingGuoMap(evt.getXiangXiZhiLiaoJingGuoMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinJianChaZongJieEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setJianChaZongJieMap(evt.getJianChaZongJieMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinDianZiYingXiangEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setDianZiYingXiangMap(evt.getDianZiYingXiangMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_GengXinQiTaCaiLiaoEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setQiTaCaiLiaoMap(evt.getQiTaCaiLiaoMap());

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ChengGongWanChengEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setWanChengBeiZhu(evt.getBeiZhu());
        record.setZhuangTai(WenZhen.ZhuangTai.YI_CHENG_GONG_WAN_CHENG);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_ZhongDuanWanChengEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.setWanChengBeiZhu(evt.getBeiZhu());
        record.setZhuangTai(WenZhen.ZhuangTai.YI_JIE_SHU_WAN_CHENG);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_KaiJuWenZhenBaoGaoEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        WenZhen.WenZhenBaoGao record2 = new WenZhen.WenZhenBaoGao();
        record2.setId(evt.getWenZhenBaoGaoId());
        record2.setZhengWen(evt.getZhengWen());
        record2.setZhangHaoId(evt.getZhangHaoId());
        record2.setShiJian(evt.getShiJian());

        record.getWenZhenBaoGaoList().add(record2);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_KaiJuZhenLiaoBaoGaoEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        WenZhen.ZhenLiaoBaoGao record2 = new WenZhen.ZhenLiaoBaoGao();
        record2.setId(evt.getZhenLiaoBaoGaoId());
        record2.setZhengWen(evt.getZhengWen());
        record2.setZhangHaoId(evt.getZhangHaoId());
        record2.setShiJian(evt.getShiJian());

        record.getZhenLiaoBaoGaoList().add(record2);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_KaiJuChuFangEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

       WenZhen.ChuFang record2 = new WenZhen.ChuFang();
        record2.setZhengWen(evt.getZhengWen());
        record2.setKaiJuZhangHaoId(evt.getZhangHaoId());
        record2.setKaiJuShiJian(evt.getShiJian());

        record2.setZhuangTai(WenZhen.ChuFang.ZhuangTai.YI_KAI_JU);

        record.setChuFang(record2);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_QueRenChuFangEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.getChuFang().setQueRenZhangHaoId(evt.getZhangHaoId());
        record.getChuFang().setQueRenShiJian(evt.getShiJian());

        record.getChuFang().setZhuangTai(WenZhen.ChuFang.ZhuangTai.YI_QUE_REN);

        repository.saveAndFlush(record);
    }

    @EventHandler
    public void on(WenZhen_QuXiaoChuFangEvt evt) {
        WenZhenView record = repository.findById(evt.getId()).get();

        record.getChuFang().setQuXiaoZhangHaoId(evt.getZhangHaoId());

        record.getChuFang().setZhuangTai(WenZhen.ChuFang.ZhuangTai.YI_QU_XIAO);

        repository.saveAndFlush(record);
    }
}
