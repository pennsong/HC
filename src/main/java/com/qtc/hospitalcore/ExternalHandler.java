package com.qtc.hospitalcore;

import com.qtc.hospitalcore.domain.ExtCheckChanPinCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYiHuRenYuanCmd;
import com.qtc.hospitalcore.domain.ExtChuangJianYongHuCmd;
import com.qtc.hospitalcore.domain.chanpin.ChanPin;
import com.qtc.hospitalcore.domain.chongFuJianCe.*;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.exception.PPDuplicationException;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan_ChuangJianCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHu_ChuangJianCmd;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.zhanghao.JueSe;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao_ChuangJianCmd;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Component
public class ExternalHandler {

    @Autowired
    private YongHuShouJiHaoRepository yongHuShouJiHaoRepository;

    @Autowired
    private YongHuOpenIdRepository yongHuOpenIdRepositoryRepository;

    @Autowired
    private UsernameRepository usernameRepository;

    @Autowired
    private YiHuRenYuanShenFenZhengHaoRepository yiHuRenYuanShenFenZhengHaoRepository;

    @Autowired
    private Repository<ZhangHao> zhangHaoRepository;

    @Autowired
    private Repository<YongHu> yongHuRepository;

    @Autowired
    private Repository<YiHuRenYuan> yiHuRenYuanRepository;

    @Autowired
    private Repository<ChanPin> chanPinRepository;

    @CommandHandler
    public void on(ExtChuangJianYongHuCmd cmd, MetaData metaData) throws Exception {
        // 条件检查

        // 检测手机号
        YongHuShouJiHao yongHuShouJiHao = new YongHuShouJiHao(cmd.getShouJiHao());
        try {
            yongHuShouJiHaoRepository.saveAndFlush(yongHuShouJiHao);
        } catch (Exception e) {
            throw new PPDuplicationException("此用户手机号码已存在");
        }

        // 检测openId
        YongHuOpenId yongHuOpenId = new YongHuOpenId(cmd.getWeiXinOpenId());
        try {
            yongHuOpenIdRepositoryRepository.saveAndFlush(yongHuOpenId);
        } catch (Exception e) {
            throw new PPDuplicationException("此用户OpenId已存在");
        }
        // 条件检查 end

        // 创建用户
        UUID yongHuId = UUID.randomUUID();
        yongHuRepository.newInstance(() -> new YongHu(
                new YongHu_ChuangJianCmd(
                        yongHuId,
                        cmd.getShouJiHao(),
                        cmd.getWeiXinOpenId()
                ),
                metaData
        ));

        // 创建帐号
        zhangHaoRepository.newInstance(() -> new ZhangHao(
                new ZhangHao_ChuangJianCmd(
                        cmd.getZhangHaoId(),
                        null,
                        null,
                        JueSe.YONG_HU,
                        yongHuId,
                        null
                ), metaData
        ));
    }

    @CommandHandler
    public void on(ExtChuangJianYiHuRenYuanCmd cmd, MetaData metaData) throws Exception {
        // 条件检查

        // 检查身份证号
        YiHuRenYuanShenFenZhengHao yiHuRenYuanShenFenZhengHao = new YiHuRenYuanShenFenZhengHao(cmd.getShenFenZhengHao());

        try {
            yiHuRenYuanShenFenZhengHaoRepository.saveAndFlush(yiHuRenYuanShenFenZhengHao);
        } catch (Exception e) {
            throw new PPDuplicationException("此身份证号已存在");
        }

        // 检查username
        Username username = new Username(cmd.getUsername());

        try {
            usernameRepository.saveAndFlush(username);
        } catch (Exception e) {
            throw new PPDuplicationException("此用户名已存在");
        }
        // 条件检查 end

        // 创建医护人员
        UUID yiHuRenYuanId = UUID.randomUUID();
        yiHuRenYuanRepository.newInstance(() -> new YiHuRenYuan(
                new YiHuRenYuan_ChuangJianCmd(
                        yiHuRenYuanId,
                        cmd.getXingMing(),
                        cmd.getShenFenZhengHao(),
                        new HashSet<>(),
                        cmd.getYiHuRenYuan_XinXiMap()
                ),
                metaData
        ));

        // 创建帐号
        zhangHaoRepository.newInstance(() -> new ZhangHao(
                new ZhangHao_ChuangJianCmd(
                        cmd.getZhangHaoId(),
                        cmd.getUsername(),
                        cmd.getPassword(),
                        JueSe.YI_HU_REN_YUAN,
                        null,
                        yiHuRenYuanId
                ), metaData
        ));
    }

    @CommandHandler
    public void on (ExtCheckChanPinCmd cmd) {
        // 如果查不到, 这里会抛异常
        Aggregate<ChanPin> chanPinAggregate = chanPinRepository.load(cmd.getId().toString());

        if (chanPinAggregate.invoke(r -> r.isDeleted())) {
            throw new PPBusinessException("产品已删除");
        }
    }
}
