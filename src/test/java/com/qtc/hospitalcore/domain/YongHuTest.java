package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.paiban.PaiBan;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ShangJiaCmd;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ShangJiaEvt;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yaopin.YaoPin;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ShanChuCmd;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ShanChuEvt;
import com.qtc.hospitalcore.domain.yonghu.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class YongHuTest {
    private FixtureConfiguration<YongHu> fixture;

    private YongHuViewRepository repository = mock(YongHuViewRepository.class);

    private YongHuEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();
    // mock data end

    private YongHu getTemplate() {
        YongHu template = new YongHu();

        template.setId(id);
        template.setShouJiHao("sj");
        template.setXingMing("xm");
        template.setShenFenZheng("sf");
        template.setWeiXinOpenId("wx");
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    private YongHuView getViewTemplate() {
        YongHuView template = new YongHuView();

        template.setId(id);
        template.setShouJiHao("sj");
        template.setXingMing("xm");
        template.setShenFenZheng("sf");
        template.setWeiXinOpenId("wx");
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YongHu.class);

        eventListener = new YongHuEventListener(repository);
    }

    @Test
    public void test_YongHu_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new YongHu_ChuangJianCmd(
                        id,
                        "sj",
                        "wx"
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YongHu record = getTemplate();

                    record.setXingMing(null);
                    record.setShenFenZheng(null);
                    record.setXinXiMap(null);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YongHu_ChuangJianEvt evt = new YongHu_ChuangJianEvt(
                id,
                "sj",
                "wx"
        );

        YongHuView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YongHuView record2 = getViewTemplate();

        record2.setXingMing(null);
        record2.setShenFenZheng(null);
        record2.setXinXiMap(null);
        // 修改record到预期结果 end

        ArgumentCaptor<YongHuView> captor = ArgumentCaptor.forClass(YongHuView.class);
        verify(repository).saveAndFlush(captor.capture());
        YongHuView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
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
                        "xm",
                        "sf",
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new YongHu_ChuangJianJiBenXinXiEvt(
                        id,
                        "xm",
                        "sf",
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectState(state -> {
                    YongHu record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YongHu_ChuangJianEvt evt = new YongHu_ChuangJianEvt(
                id,
                "sj",
                "wx"
        );

        YongHuView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YongHuView record2 = getViewTemplate();

        record2.setXingMing(null);
        record2.setShenFenZheng(null);
        record2.setXinXiMap(null);
        // 修改record到预期结果 end

        ArgumentCaptor<YongHuView> captor = ArgumentCaptor.forClass(YongHuView.class);
        verify(repository).saveAndFlush(captor.capture());
        YongHuView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_YongHu_ShanChuCmd() {

        fixture.givenState(() -> {
            YongHu record = getTemplate();

            return record;
        })
                .when(new YongHu_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YongHu record = getTemplate();

                    record.delete();
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YongHu_ShanChuEvt evt = new YongHu_ShanChuEvt(
                id
        );

        YongHuView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YongHuView record2 = getViewTemplate();

        record2.delete();
        // 修改record到预期结果 end

        ArgumentCaptor<YongHuView> captor = ArgumentCaptor.forClass(YongHuView.class);
        verify(repository).saveAndFlush(captor.capture());
        YongHuView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }
}
