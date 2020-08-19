package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.ChanPin;
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

    // mock data
    UUID id = UUID.randomUUID();

    String xingMing = "x";
    String shenFenZhengHao = "s";
    String shouJiHao = "sj";
    Map<String, Object> jiBenXinXiMap = PPUtil.stringToMap("A:1, B:1");
    Map<String, Object> jianKangXinXiMap = PPUtil.stringToMap("C:1, D:1");

    // mock data end

    private JianKangDangAn getTemplate() {
        JianKangDangAn template = new JianKangDangAn();
        template.setId(id);
        template.setXingMing(xingMing);
        template.setShenFenZhengHao(shenFenZhengHao);
        template.setShouJiHao(shouJiHao);
        template.setJiBenXinXiMap(jiBenXinXiMap);
        template.setJianKangXinXiMap(jianKangXinXiMap);

        template.setZhuangTai(JianKangDangAn.ZhuangTai.YI_CHUANG_JIAN);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(JianKangDangAn.class);
    }

    @Test
    public void test_JianKangDangAn_ChuangJianCmd() {

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
                    JianKangDangAn record = getTemplate();
                    record.setJianKangXinXiMap(null);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_JianKangDangAn_GengXinJianKangXinXiCmd() {
        // mock data
        Map<String, Object> jianKangXinXiMap2 = PPUtil.stringToMap("D:2, F: 2");

        // mock data end
        fixture.givenState(() -> {
            JianKangDangAn record = getTemplate();

            return record;
        })
                .when(new JianKangDangAn_GengXinJianKangXinXiCmd(
                        id,
                        jianKangXinXiMap2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new JianKangDangAn_GengXinJianKangXinXiEvt(
                        id,
                        jianKangXinXiMap2
                ))
                .expectState(state -> {
                    JianKangDangAn record = getTemplate();
                    record.setJianKangXinXiMap(jianKangXinXiMap2);
                    record.setZhuangTai(JianKangDangAn.ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN);

                    // perform assertions
                    assertEquals(record, state);
                });
    }


}
