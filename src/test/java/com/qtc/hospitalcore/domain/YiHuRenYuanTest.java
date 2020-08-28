package com.qtc.hospitalcore.domain;

import com.google.common.collect.ImmutableMap;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yihurenyuan.*;
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


public class YiHuRenYuanTest {
    private FixtureConfiguration<YiHuRenYuan> fixture;

    private YiHuRenYuanViewRepository repository = mock(YiHuRenYuanViewRepository.class);

    private YiHuRenYuanEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();
    // mock data end

    private YiHuRenYuan getTemplate() {
        YiHuRenYuan template = new YiHuRenYuan();

        template.setId(id);
        template.setXingMing("xm");
        template.setShenFenZheng("sf");
        template.setQuanXianMap(
                ImmutableMap.of(
                        QuanXian.BIAN_JI_BING_LI, true,
                        QuanXian.KAI_JU_CHU_FANG, true,
                        QuanXian.QUE_REN_CHU_FANG, true,
                        QuanXian.WEN_ZHEN, true
                )
        );
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    private YiHuRenYuanView getViewTemplate() {
        YiHuRenYuanView template = new YiHuRenYuanView();

        template.setId(id);
        template.setXingMing("xm");
        template.setShenFenZheng("sf");
        template.setQuanXianMap(
                ImmutableMap.of(
                        QuanXian.BIAN_JI_BING_LI, true,
                        QuanXian.KAI_JU_CHU_FANG, true,
                        QuanXian.QUE_REN_CHU_FANG, true,
                        QuanXian.WEN_ZHEN, true
                )
        );
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YiHuRenYuan.class);

        eventListener = new YiHuRenYuanEventListener(repository);
    }

    @Test
    public void test_YiHuRenYuan_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new YiHuRenYuan_ChuangJianCmd(
                        id,
                        "xm",
                        "sf",
                        ImmutableMap.of(
                                QuanXian.BIAN_JI_BING_LI, true,
                                QuanXian.KAI_JU_CHU_FANG, true,
                                QuanXian.QUE_REN_CHU_FANG, true,
                                QuanXian.WEN_ZHEN, true
                        ),
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YiHuRenYuan record = getTemplate();

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YiHuRenYuan_ChuangJianEvt evt = new YiHuRenYuan_ChuangJianEvt(
                id,
                "xm",
                "sf",
                ImmutableMap.of(
                        QuanXian.BIAN_JI_BING_LI, true,
                        QuanXian.KAI_JU_CHU_FANG, true,
                        QuanXian.QUE_REN_CHU_FANG, true,
                        QuanXian.WEN_ZHEN, true
                ),
                PPUtil.stringToMap("A:1, B:1")
        );

        YiHuRenYuanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YiHuRenYuanView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<YiHuRenYuanView> captor = ArgumentCaptor.forClass(YiHuRenYuanView.class);
        verify(repository).saveAndFlush(captor.capture());
        YiHuRenYuanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_YiHuRenYuan_GengXinCmd() {

        fixture.givenState(() -> {
            YiHuRenYuan record = getTemplate();

            return record;
        })
                .when(new YiHuRenYuan_GengXinCmd(
                        id,
                        "xm2",
                        "sf2",
                        PPUtil.stringToMap("B:2, C:2")
                ))
                .expectSuccessfulHandlerExecution()

                .expectState(state -> {
                    YiHuRenYuan record = getTemplate();

                    record.setXingMing("xm2");
                    record.setShenFenZheng("sf2");
                    record.setXinXiMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_YiHuRenYuan_SheZhiQuanXianCmd() {

        fixture.givenState(() -> {
            YiHuRenYuan record = getTemplate();

            return record;
        })
                .when(new YiHuRenYuan_SheZhiQuanXianCmd(
                        id,
                        ImmutableMap.of(
                                QuanXian.BIAN_JI_BING_LI, true,
                                QuanXian.KAI_JU_CHU_FANG, true,
                                QuanXian.QUE_REN_CHU_FANG, true
                        )
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YiHuRenYuan record = getTemplate();

                    record.setQuanXianMap(
                            ImmutableMap.of(
                                    QuanXian.BIAN_JI_BING_LI, true,
                                    QuanXian.KAI_JU_CHU_FANG, true,
                                    QuanXian.QUE_REN_CHU_FANG, true
                            )
                    );

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YiHuRenYuan_SheZhiQuanXianEvt evt = new YiHuRenYuan_SheZhiQuanXianEvt(
                id,
                new HashMap<>(
                        ImmutableMap.of(
                                QuanXian.BIAN_JI_BING_LI, true,
                                QuanXian.KAI_JU_CHU_FANG, true,
                                QuanXian.QUE_REN_CHU_FANG, true
                        )
                )
        );

        YiHuRenYuanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YiHuRenYuanView record2 = getViewTemplate();

        record2.setQuanXianMap(
                ImmutableMap.of(
                        QuanXian.BIAN_JI_BING_LI, true,
                        QuanXian.KAI_JU_CHU_FANG, true,
                        QuanXian.QUE_REN_CHU_FANG, true
                )
        );
        // 修改record到预期结果 end

        ArgumentCaptor<YiHuRenYuanView> captor = ArgumentCaptor.forClass(YiHuRenYuanView.class);
        verify(repository).saveAndFlush(captor.capture());
        YiHuRenYuanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }
}
