package com.qtc.hospitalcore.domain;

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

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ZhangHao.class);
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd() {
        // mock data
        UUID zhangHaoId = UUID.randomUUID();

        UUID yongHuId = UUID.randomUUID();

        String username = "u1";
        String password = "p1";
        ZhangHao.JueSe jueSe = ZhangHao.JueSe.YONG_HU;

        // mock data end

        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        zhangHaoId,
                        username,
                        password,
                        jueSe,
                        yongHuId,
                        null
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ZhangHao_ChuangJianEvt(
                        zhangHaoId,
                        username,
                        password,
                        jueSe,
                        yongHuId,
                        null
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(zhangHaoId, state.getId());
                    assertEquals(username, state.getUsername());
                    assertEquals(password, state.getPassword());
                    assertEquals(new HashSet<>(Arrays.asList(ZhangHao.JueSe.YONG_HU)), state.getJueSeSet());
                    assertEquals(yongHuId, state.getYongHuId());
                    assertEquals(null, state.getYiHuRenYuanId());
                });
    }

    public void test_ZhangHao_SheZhiMiMaCmd() {
        // mock data
        UUID zhangHaoId = UUID.randomUUID();

        String username = "u1";
        String password = "p1";
        Set<ZhangHao.JueSe> jueSeSet = new HashSet<>(Arrays.asList(ZhangHao.JueSe.YI_HU_REN_YUAN));

        String password2 = "p1";

        // mock data end

        fixture.givenState(() -> {
            ZhangHao record = new ZhangHao();
            record.setId(zhangHaoId);
            record.setUsername(username);
            record.setPassword(password);
            record.setJueSeSet(jueSeSet);

            return record;
        })
                .when(new ZhangHao_SheZhiMiMaCmd(
                        zhangHaoId,
                        password2

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ZhangHao_SheZhiMiMaEvt(
                        zhangHaoId,
                       password2
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(password2, state.getPassword());
                });
    }


}
