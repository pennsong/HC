package com.qtc.hospitalcore;

import com.google.common.collect.ImmutableMap;
import com.qtc.hospitalcore.domain.ExtChuangJianYiHuRenYuanCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPinViewRepository;
import com.qtc.hospitalcore.domain.chanpin.ChanPin_ChuangJianCmd;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAn_ChuangJianCmd;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ChuangJianCmd;
import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.wenzhen.WenZhen_ChuangJianCmd;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ChuangJianCmd;
import com.qtc.hospitalcore.domain.yihurenyuan.QuanXian;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan_SheZhiQuanXianCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.yonghu.YongHuViewRepository;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ChuangJianJiBenXinXiCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.UUID;

@Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private PPCommandGateway ppCommandGateway;

    @Autowired
    private ChanPinViewRepository chanPinViewRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO: PP开发完成后去掉

        // 等待query model同步完成
        Thread.sleep(1000);

        if (chanPinViewRepository.findByMingCheng("产品1").isPresent()) {
            log.info("dev data inited");
            return;
        }

        // 产品
        UUID id = UUID.randomUUID();

        ppCommandGateway.sendAndWait(
                new ChanPin_ChuangJianCmd(
                        id,
                        "产品1",
                        "大类1",
                        "小类1",
                        new BigDecimal(10),
                        new BigDecimal(100),
                        PPUtil.stringToMap("A:1, B:1")
                )
        );

        for (int i = 0; i < 30; i++) {
            // 用户
            UUID zhangHaoId = UUID.randomUUID();
            UUID yongHuId = UUID.randomUUID();
            ppCommandGateway.sendAndWait(
                    new ExtChuangJianYongHuCmd(
                            zhangHaoId,
                            yongHuId,
                            "" + i,
                            "wx" + i
                    )
            );

            ppCommandGateway.sendAndWait(
                    new YongHu_ChuangJianJiBenXinXiCmd(
                            yongHuId,
                            "xm" + i,
                            "sfz" + i,
                            PPUtil.stringToMap("A:1, B:1")
                    )
            );

            // 健康档案
            UUID jianKangDangAnId = UUID.randomUUID();
            ppCommandGateway.sendAndWait(
                    new JianKangDangAn_ChuangJianCmd(
                            jianKangDangAnId,
                            yongHuId,
                            "xm" + i,
                            "sfz" + i,
                            "s" + i,
                            PPUtil.stringToMap("A:1, B:1")
                    )
            );

            // 问诊
            UUID wenZhenId = UUID.randomUUID();
            UUID chanPinId = UUID.randomUUID();
            UUID paiBanId = UUID.randomUUID();

            ppCommandGateway.sendAndWait(
                    new WenZhen_ChuangJianCmd(
                            wenZhenId,
                            jianKangDangAnId,
                            chanPinId,
                            paiBanId,
                            new BigDecimal(10),
                            new BigDecimal(100),
                            "cp" + i,
                            "cpJson" + i,
                            "paiBanJson" + i,
                            PPUtil.stringToMap("A:" + i + ", B:" + i)
                    )
            );
        }

        for (int i = 0; i < 30; i++) {
            // 医护人员
            UUID zhangHaoId = UUID.randomUUID();
            UUID yiHuRenYuanId = UUID.randomUUID();
            ppCommandGateway.sendAndWait(
                    new ExtChuangJianYiHuRenYuanCmd(
                            zhangHaoId,
                            yiHuRenYuanId,
                            "un" + i,
                            "ps" + i,
                            "xm" + i,
                            "sf" + i,
                            PPUtil.stringToMap("A:" + i + ", B:" + i)
                    )
            );

            ppCommandGateway.sendAndWait(
                    new YiHuRenYuan_SheZhiQuanXianCmd(
                            yiHuRenYuanId,
                            new HashMap(
                                    ImmutableMap.of(
                                            QuanXian.BIAN_JI_BING_LI, true,
                                            QuanXian.KAI_JU_CHU_FANG, true,
                                            QuanXian.QUE_REN_CHU_FANG, true,
                                            QuanXian.WEN_ZHEN, true
                                    )
                            )
                    )
            );
        }

        // 药品
        for (int i = 0; i < 30; i++) {
            UUID yaoPinId = UUID.randomUUID();

            ppCommandGateway.sendAndWait(
                    new YaoPin_ChuangJianCmd(
                            yaoPinId,
                            "ypmc" + i,
                            "dlx",
                            "xlx",
                            PPUtil.stringToMap("A:" + i + ", B:" + i)
                    )
            );
        }

        // 产品
        for (int i = 0; i < 30; i++) {
            UUID chanPinId = UUID.randomUUID();

            ppCommandGateway.sendAndWait(
                    new ChanPin_ChuangJianCmd(
                            chanPinId,
                            "cpm" + i,
                            "dl",
                            "xl",
                            new BigDecimal(10),
                            new BigDecimal(100),
                            PPUtil.stringToMap("A:" + i + ", B:" + i)
                    )
            );

            // 排班
            UUID paiBanId = UUID.randomUUID();
            ppCommandGateway.sendAndWait(
                    new PaiBan_ChuangJianCmd(
                            paiBanId,
                            chanPinId,
                            new BigDecimal(10),
                            new BigDecimal(100),
                            "ys" + i,
                            OffsetDateTime.now().plusDays(1),
                            PPUtil.stringToMap("A:" + i + ", B:" + i)
                    )
            );
        }
    }
}
