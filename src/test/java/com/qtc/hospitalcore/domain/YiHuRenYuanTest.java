package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yihurenyuan.*;
import com.qtc.hospitalcore.domain.yonghu.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class YiHuRenYuanTest {
    private FixtureConfiguration<YiHuRenYuan> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YiHuRenYuan.class);
    }

    @Test
    public void t_YiHuRenYuan_ChuangJianCmd() {
        // mock data
        UUID id = UUID.randomUUID();
        String xingMing = "x1";
        String shenFenZhengHao = "s1";
        Set<YiHuRenYuan.QuanXian> quanXianSet = new HashSet<>(Arrays.asList(YiHuRenYuan.QuanXian.BIAN_JI_BING_LI, YiHuRenYuan.QuanXian.KAI_JU_CHU_FANG));
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:2");

        // mock data end

        fixture.givenNoPriorActivity()
                .when(new YiHuRenYuan_ChuangJianCmd(
                        id,
                        xingMing,
                        shenFenZhengHao,
                        quanXianSet,
                        xinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YiHuRenYuan_ChuangJianEvt(
                        id,
                        xingMing,
                        shenFenZhengHao,
                        quanXianSet,
                        xinXiMap
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(id, state.getId());
                    assertEquals(xingMing, state.getXingMing());
                    assertEquals(shenFenZhengHao, state.getShenFenZhengHao());
                    assertEquals(quanXianSet, state.getQuanXianSet());
                    assertEquals(xinXiMap, state.getXinXiMap());
                });
    }

    @Test
    public void t_YiHuRenYuan_GengXinCmd() {
        // mock data
        UUID id = UUID.randomUUID();
        String xingMing1 = "x1";
        String shenFenZhengHao1 = "s1";
        Set<YiHuRenYuan.QuanXian> quanXianSet1 = new HashSet<>(Arrays.asList(YiHuRenYuan.QuanXian.BIAN_JI_BING_LI, YiHuRenYuan.QuanXian.KAI_JU_CHU_FANG));
        Map<String, Object> xinXiMap1 = PPUtil.stringToMap("A:1, B:1");

        String xingMing2 = "x2";
        String shenFenZhengHao2 = "s2";
        Map<String, Object> xinXiMap2 = PPUtil.stringToMap("B:2, C:2");

        // mock data end

        fixture.givenState(() -> {
            YiHuRenYuan record = new YiHuRenYuan();
            record.setId(id);
            record.setXingMing(xingMing1);
            record.setShenFenZhengHao(shenFenZhengHao1);
            record.setQuanXianSet(quanXianSet1);
            record.setXinXiMap(xinXiMap1);

            return record;
        })
                .when(new YiHuRenYuan_GengXinCmd(
                        id,
                        xingMing2,
                        shenFenZhengHao2,
                        xinXiMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YiHuRenYuan_GengXinEvt(
                        id,
                        xingMing2,
                        shenFenZhengHao2,
                        xinXiMap2
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(id, state.getId());
                    assertEquals(xingMing2, state.getXingMing());
                    assertEquals(shenFenZhengHao2, state.getShenFenZhengHao());
                    assertEquals(xinXiMap2, state.getXinXiMap());
                });
    }

    @Test
    public void t_YiHuRenYuan_SheZhiQuanXianCmd() {
        // mock data
        UUID id = UUID.randomUUID();
        String xingMing = "x1";
        String shenFenZhengHao = "s1";
        Set<YiHuRenYuan.QuanXian> quanXianSet = new HashSet<>(Arrays.asList(YiHuRenYuan.QuanXian.BIAN_JI_BING_LI, YiHuRenYuan.QuanXian.KAI_JU_CHU_FANG));
        Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

        Set<YiHuRenYuan.QuanXian> quanXianSet2 = new HashSet<>(Arrays.asList(YiHuRenYuan.QuanXian.BIAN_JI_BING_LI, YiHuRenYuan.QuanXian.QUE_REN_CHU_FANG));

        // mock data end

        fixture.givenState(() -> {
            YiHuRenYuan record = new YiHuRenYuan();
            record.setId(id);
            record.setXingMing(xingMing);
            record.setShenFenZhengHao(shenFenZhengHao);
            record.setQuanXianSet(quanXianSet);
            record.setXinXiMap(xinXiMap);

            return record;
        })
                .when(new YiHuRenYuan_SheZhiQuanXianCmd(
                        id,
                        quanXianSet2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YiHuRenYuan_SheZhiQuanXianEvt(
                        id,
                        quanXianSet2
                ))
                .expectState(state -> {
                    // perform assertions
                    assertEquals(quanXianSet2, state.getQuanXianSet());
                });
    }
}
