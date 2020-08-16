package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.paiban.PaiBan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan_GengXinCmd;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan_GengXinEvt;
import com.qtc.hospitalcore.domain.zhanghao.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZhangHaoTest {
    private FixtureConfiguration<ZhangHao> fixture;

    // mock data
    UUID id = UUID.randomUUID();

    String username = "u";
    String password = "p";
    ZhangHao.JueSe jueSe = ZhangHao.JueSe.YONG_HU;
    Set<ZhangHao.JueSe> jueSeSet = new HashSet<>(Arrays.asList(ZhangHao.JueSe.YONG_HU));
    UUID yongHuId = UUID.randomUUID();
    UUID yiHuRenYuanId = UUID.randomUUID();

    ZhangHao.JueSe jueSe2 = ZhangHao.JueSe.YI_HU_REN_YUAN;
    Set<ZhangHao.JueSe> jueSeSet2 = new HashSet<>(Arrays.asList(ZhangHao.JueSe.YI_HU_REN_YUAN));

    // mock data end

    private ZhangHao getTemplate() {
        ZhangHao template = new ZhangHao();
        template.setId(id);
        template.setUsername(username);
        template.setPassword(password);
        template.setJueSeSet(jueSeSet);
        template.setYongHuId(yongHuId);
        template.setYiHuRenYuanId(yiHuRenYuanId);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ZhangHao.class);
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd_1() {

        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        id,
                        username,
                        password,
                        jueSe,
                        yongHuId,
                        null
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ZhangHao_ChuangJianEvt(
                        id,
                        username,
                        password,
                        jueSe,
                        yongHuId,
                        null
                ))
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.setYiHuRenYuanId(null);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd_2() {

        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        id,
                        username,
                        password,
                        jueSe2,
                        null,
                        yiHuRenYuanId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ZhangHao_ChuangJianEvt(
                        id,
                        username,
                        password,
                        jueSe2,
                        null,
                        yiHuRenYuanId
                ))
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.setYongHuId(null);
                    record.setJueSeSet(jueSeSet2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ZhangHao_SheZhiMiMaCmd() {
        // mock data
        String password2 = "p2";

        // mock data end

        fixture.givenState(() -> {
            ZhangHao record = getTemplate();

            return record;
        })
                .when(new ZhangHao_SheZhiMiMaCmd(
                        id,
                        password2

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ZhangHao_SheZhiMiMaEvt(
                        id,
                        password2
                ))
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.setPassword(password2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }


}
