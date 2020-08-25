package com.qtc.hospitalcore;

import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPinViewRepository;
import com.qtc.hospitalcore.domain.chanpin.ChanPin_ChuangJianCmd;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAn_ChuangJianCmd;
import com.qtc.hospitalcore.domain.util.PPCommandGateway;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
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

        // 用户
        UUID zhangHaoId = UUID.randomUUID();
        UUID yongHuId = UUID.randomUUID();
        ppCommandGateway.sendAndWait(
                new ExtChuangJianYongHuCmd(
                        zhangHaoId,
                        yongHuId,
                        "13800000000",
                        "wx"
                )
        );

        ppCommandGateway.sendAndWait(
                new YongHu_ChuangJianJiBenXinXiCmd(
                       yongHuId,
                        "xm",
                        "sfz",
                        PPUtil.stringToMap("A:2, B:2")
                )
        );

        // 健康档案
        UUID jianKangDangAnId = UUID.randomUUID();
        ppCommandGateway.sendAndWait(
                new JianKangDangAn_ChuangJianCmd(
                        jianKangDangAnId,
                        yongHuId,
                        "xm2",
                        "sfz2",
                        "13800000002",
                        PPUtil.stringToMap("A:3, B:3")
                )
        );
    }
}
