package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.paiban.PaiBan;
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

    // mock data
    UUID id = UUID.randomUUID();
    String xingMing = "x";
    String shenFenZhengHao = "s";
    Set<YiHuRenYuan.QuanXian> quanXianSet = new HashSet<>(Arrays.asList(YiHuRenYuan.QuanXian.WEN_ZHEN, YiHuRenYuan.QuanXian.KAI_JU_CHU_FANG));
    Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

    // mock data end

    private YiHuRenYuan getTemplate() {
        YiHuRenYuan template = new YiHuRenYuan();
        template.setId(id);
        template.setXingMing(xingMing);
        template.setShenFenZhengHao(shenFenZhengHao);
        template.setQuanXianSet(quanXianSet);
        template.setXinXiMap(xinXiMap);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YiHuRenYuan.class);
    }

    @Test
    public void test_YiHuRenYuan_ChuangJianCmd() {

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
                    YiHuRenYuan record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_YiHuRenYuan_GengXinCmd() {
        // mock data
        String xingMing2 = "x2";
        String shenFenZhengHao2 = "s2";
        Map<String, Object> xinXiMap2 = PPUtil.stringToMap("B:2, C:2");

        // mock data end

        fixture.givenState(() -> {
            YiHuRenYuan record = getTemplate();

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
                    YiHuRenYuan record = getTemplate();
                    record.setXingMing(xingMing2);
                    record.setShenFenZhengHao(shenFenZhengHao2);
                    record.setXinXiMap(xinXiMap2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void t_YiHuRenYuan_SheZhiQuanXianCmd() {
        // mock data
        Set<YiHuRenYuan.QuanXian> quanXianSet2 = new HashSet<>(Arrays.asList(YiHuRenYuan.QuanXian.BIAN_JI_BING_LI, YiHuRenYuan.QuanXian.QUE_REN_CHU_FANG));

        // mock data end

        fixture.givenState(() -> {
            YiHuRenYuan record = getTemplate();

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
                    YiHuRenYuan record = getTemplate();
                    record.setQuanXianSet(quanXianSet2);

                    // perform assertions
                    assertEquals(record, state);
                });
    }
}
