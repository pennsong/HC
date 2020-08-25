package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.jiankangdangan.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yaopin.YaoPin;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ChuangJianEvt;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class JianKangDangAnTest {
    private FixtureConfiguration<JianKangDangAn> fixture;

    private JianKangDangAnViewRepository repository = mock(JianKangDangAnViewRepository.class);

    private JianKangDangAnEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();
    UUID yongHuId = UUID.randomUUID();
    // mock data end

    private JianKangDangAn getTemplate() {
        JianKangDangAn template = new JianKangDangAn();

        template.setId(id);
        template.setXingMing("xm");
        template.setShenFenZhengHao("sf");
        template.setShouJiHao("sj");
        template.setJiBenXinXiMap(PPUtil.stringToMap("A:1, B:1"));
        template.setJianKangXinXiMap(PPUtil.stringToMap("C:1, D:1"));

        template.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

        return template;
    }

    private JianKangDangAnView getViewTemplate() {
        JianKangDangAnView template = new JianKangDangAnView();

        template.setId(id);
        template.setYongHuId(yongHuId);
        template.setXingMing("xm");
        template.setShenFenZhengHao("sf");
        template.setShouJiHao("sj");
        template.setJiBenXinXiMap(PPUtil.stringToMap("A:1, B:1"));
        template.setJianKangXinXiMap(PPUtil.stringToMap("C:1, D:1"));

        template.setZhuangTai(ZhuangTai.YI_CHUANG_JIAN);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(JianKangDangAn.class);

        eventListener = new JianKangDangAnEventListener(repository);
    }

    @Test
    public void test_JianKangDangAn_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new JianKangDangAn_ChuangJianCmd(
                        id,
                        yongHuId,
                        "xm",
                        "sf",
                        "sj",
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    JianKangDangAn record = getTemplate();

                    record.setJianKangXinXiMap(null);
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        JianKangDangAn_ChuangJianEvt evt = new JianKangDangAn_ChuangJianEvt(
                id,
                yongHuId,
                "xm",
                "sf",
                "sj",
                PPUtil.stringToMap("A:1, B:1")
        );

        JianKangDangAnView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        JianKangDangAnView record2 = getViewTemplate();

        record2.setJianKangXinXiMap(null);
        // 修改record到预期结果 end

        ArgumentCaptor<JianKangDangAnView> captor = ArgumentCaptor.forClass(JianKangDangAnView.class);
        verify(repository).saveAndFlush(captor.capture());
        JianKangDangAnView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_JianKangDangAn_GengXinJianKangXinXiCmd() {

        fixture.givenState(() -> {
            JianKangDangAn record = getTemplate();

            return record;
        })
                .when(new JianKangDangAn_GengXinJianKangXinXiCmd(
                        id,
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    JianKangDangAn record = getTemplate();

                    record.setJianKangXinXiMap(PPUtil.stringToMap("B:2, C:2"));

                    record.setZhuangTai(ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN);
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        JianKangDangAn_GengXinJianKangXinXiEvt evt = new JianKangDangAn_GengXinJianKangXinXiEvt(
                id,
                PPUtil.stringToMap("B:2, C:2")
        );

        JianKangDangAnView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        JianKangDangAnView record2 = getViewTemplate();

        record2.setJianKangXinXiMap(PPUtil.stringToMap("B:2, C:2"));

        record2.setZhuangTai(ZhuangTai.JIAN_KANG_XIN_XI_YI_GENG_XIN);
        // 修改record到预期结果 end

        ArgumentCaptor<JianKangDangAnView> captor = ArgumentCaptor.forClass(JianKangDangAnView.class);
        verify(repository).saveAndFlush(captor.capture());
        JianKangDangAnView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }
}
