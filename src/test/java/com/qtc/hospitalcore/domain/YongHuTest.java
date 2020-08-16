package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.paiban.PaiBan;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ShangJiaCmd;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ShangJiaEvt;
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

    // mock data
    UUID id = UUID.randomUUID();
    String shouJiHao = "s";
    String xingMing = "x";
    String shenFenZheng = "sf";
    String weiXinOpenId = "weiXinOpenId1";
    Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

    // mock data end

    private YongHu getTemplate() {
        YongHu template = new YongHu();
        template.setId(id);
        template.setShouJiHao(shouJiHao);
        template.setXingMing(xingMing);
        template.setShenFenZheng(shenFenZheng);
        template.setWeiXinOpenId(weiXinOpenId);
        template.setXinXiMap(xinXiMap);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YongHu.class);
    }

    @Test
    public void test_YongHu_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new YongHu_ChuangJianCmd(
                        id,
                        shouJiHao,
                        weiXinOpenId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YongHu_ChuangJianEvt(
                        id,
                        shouJiHao,
                        weiXinOpenId
                ))
                .expectState(state -> {
                    YongHu record = getTemplate();
                    record.setXingMing(null);
                    record.setShenFenZheng(null);
                    record.setXinXiMap(null);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_YongHu_ChuangJianJiBenXinXiCmd() {

        fixture.givenState(() -> {
            YongHu record = getTemplate();
            record.setXingMing(null);
            record.setShenFenZheng(null);
            record.setXinXiMap(null);

            return record;
        })
                .when(new YongHu_ChuangJianJiBenXinXiCmd(
                        id,
                        xingMing,
                        shenFenZheng,
                        xinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YongHu_ChuangJianJiBenXinXiEvt(
                        id,
                        xingMing,
                        shenFenZheng,
                        xinXiMap
                ))
                .expectState(state -> {
                    YongHu record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }
}
