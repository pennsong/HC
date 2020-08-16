package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.jiankangdangan.*;
import com.qtc.hospitalcore.domain.jiankangdangan.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.zhanghao.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JianKangDangAnTest {
    private FixtureConfiguration<JianKangDangAn> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(JianKangDangAn.class);
    }

    @Test
    public void test_JianKangDangAn_ChuangJianCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String xingMing = "x";
        String shenFenZhengHao = "s";
        String shouJiHao = "sj";
        Map<String, Object> jiBenXinXiMap = PPUtil.stringToMap("A:1, B:1");


        // mock data end

        fixture.givenNoPriorActivity()
                .when(new JianKangDangAn_ChuangJianCmd(
                        id,
                        xingMing,
                        shenFenZhengHao,
                        shouJiHao,
                        jiBenXinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new JianKangDangAn_ChuangJianEvt(
                        id,
                        xingMing,
                        shenFenZhengHao,
                        shouJiHao,
                        jiBenXinXiMap
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(xingMing, state.getXingMing());
                    assertEquals(shenFenZhengHao, state.getShenFenZhengHao());
                    assertEquals(shouJiHao, state.getShouJiHao());
                    assertEquals(jiBenXinXiMap, state.getJiBenXinXiMap());
                });
    }

    @Test
    public void test_JianKangDangAn_GengXinJianKangXinXiCmd() {
        // mock data
        UUID id = UUID.randomUUID();

        String xingMing = "x";
        String shenFenZhengHao = "s";
        String shouJiHao = "sj";
        Map<String, Object> jiBenXinXiMap = PPUtil.stringToMap("A:1, B:1");
        Map<String, Object> jianKangXinXiMap = PPUtil.stringToMap("C:1, D:1");


        // mock data end
        fixture.givenState(() -> {
            JianKangDangAn record = new JianKangDangAn();
            record.setId(id);
            record.setXingMing(xingMing);
            record.setShenFenZhengHao(shenFenZhengHao);
            record.setShouJiHao(shouJiHao);
            record.setJiBenXinXiMap(jiBenXinXiMap);
            return record;
        })
                .when(new JianKangDangAn_GengXinJianKangXinXiCmd(
                        id,
                        jianKangXinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new JianKangDangAn_GengXinJianKangXinXiEvt(
                        id,
                        jianKangXinXiMap
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(xingMing, state.getXingMing());
                    assertEquals(shenFenZhengHao, state.getShenFenZhengHao());
                    assertEquals(shouJiHao, state.getShouJiHao());
                    assertEquals(jiBenXinXiMap, state.getJiBenXinXiMap());
                    assertEquals(jianKangXinXiMap, state.getJianKangXinXiMap());
                });
    }


}
