package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yonghu.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class YongHuTest {
    private FixtureConfiguration<YongHu> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YongHu.class);
    }

    @Test
    public void test_YongHu_ChuangJianCmd() {
        // mock data
        UUID uuid = UUID.randomUUID();
        String shouJiHaoMa = "123";
        String weiXinOpenId = "weiXinOpenId1";

        // mock data end

        fixture.givenNoPriorActivity()
                .when(new YongHu_ChuangJianCmd(uuid, shouJiHaoMa, weiXinOpenId))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YongHu_ChuangJianEvt(uuid, shouJiHaoMa, weiXinOpenId))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(shouJiHaoMa, state.getShouJiHao());
                    assertEquals(weiXinOpenId, state.getWeiXinOpenId());
                });
    }

    @Test
    public void test_YongHu_ChuangJianJiBenXinXiCmd() {
        // mock data
        UUID uuid = UUID.randomUUID();
        String shouJiHaoMa = "123";
        String weiXinOpenId = "weiXinOpenId1";

        String xingMing = "x1";
        String shenFenZheng = "s1";
        Map map = PPUtil.stringToMap("A: 1, B: 2");
        // mock data end

        fixture.givenCommands(new YongHu_ChuangJianCmd(uuid, shouJiHaoMa, weiXinOpenId))
                .when(new YongHu_ChuangJianJiBenXinXiCmd(uuid, xingMing, shenFenZheng, map))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YongHu_ChuangJianJiBenXinXiEvt(uuid, xingMing, shenFenZheng, map))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(shouJiHaoMa, state.getShouJiHao());
                    assertEquals(weiXinOpenId, state.getWeiXinOpenId());

                    assertEquals(xingMing, state.getXingMing());
                    assertEquals(shenFenZheng, state.getShenFenZheng());
                    assertEquals(map, state.getXinXiMap());
                });
    }
}
