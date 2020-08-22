package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.jiankangdangan.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class JianKangDangAnTest {
    private FixtureConfiguration<JianKangDangAn> fixture;

    private JianKangDangAnViewRepository repository = mock(JianKangDangAnViewRepository.class);

    private JianKangDangAnEventListener eventListener;
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

    private JianKangDangAnView getViewTemplate() {
        JianKangDangAnView template = new JianKangDangAnView();
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

        eventListener = new JianKangDangAnEventListener(repository);
    }

    @Test
    public void test_JianKangDangAn_ChuangJianCmd() {
        // mock data
        JianKangDangAn_ChuangJianEvt evt = new JianKangDangAn_ChuangJianEvt(
                id,
                xingMing,
                shenFenZhengHao,
                shouJiHao,
                jiBenXinXiMap
        );
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
                .expectEvents(evt)
                .expectState(state -> {
                    JianKangDangAn record = getTemplate();
                    record.setJianKangXinXiMap(null);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        eventListener.on(evt);

        // 修改record到预期结果
        JianKangDangAnView record2 = getViewTemplate();
        record2.setJianKangXinXiMap(null);
        // 修改record到预期结果 end

        ArgumentCaptor<JianKangDangAnView> captor = ArgumentCaptor.forClass(JianKangDangAnView.class);

        verify(repository).saveAndFlush(captor.capture());

        JianKangDangAnView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_JianKangDangAn_GengXinJianKangXinXiCmd() {
        // mock data
        Map<String, Object> jianKangXinXiMap2 = PPUtil.stringToMap("D:2, F: 2");

        JianKangDangAn_GengXinJianKangXinXiEvt evt = new JianKangDangAn_GengXinJianKangXinXiEvt(
                id,
                jianKangXinXiMap2
        );
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
                .expectEvents(evt)
                .expectState(state -> {
                    JianKangDangAn record = getTemplate();
                    record.setJianKangXinXiMap(jianKangXinXiMap2);
                    record.setZhuangTai(JianKangDangAn.ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        JianKangDangAnView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        JianKangDangAnView record2 = getViewTemplate();
        record2.setJianKangXinXiMap(jianKangXinXiMap2);
        record2.setZhuangTai(JianKangDangAn.ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN);
        // 修改record到预期结果 end

        ArgumentCaptor<JianKangDangAnView> captor = ArgumentCaptor.forClass(JianKangDangAnView.class);

        verify(repository).saveAndFlush(captor.capture());

        JianKangDangAnView result = captor.getValue();

        assertEquals(record2, result);
    }


}
