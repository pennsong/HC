package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yaopin.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class YaoPinTest {
    private FixtureConfiguration<YaoPin> fixture;

    private YaoPinViewRepository repository = mock(YaoPinViewRepository.class);

    private YaoPinEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();
    // mock data end

    private YaoPin getTemplate() {
        YaoPin template = new YaoPin();

        template.setId(id);
        template.setZhuangTai(YaoPin.ZhuangTai.ZAI_SHOU);
        template.setMingCheng("mc");
        template.setDaLeiXing("dl");
        template.setXiaoLeiXing("xl");
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    private YaoPinView getViewTemplate() {
        YaoPinView template = new YaoPinView();

        template.setId(id);
        template.setZhuangTai(YaoPin.ZhuangTai.ZAI_SHOU);
        template.setMingCheng("mc");
        template.setDaLeiXing("dl");
        template.setXiaoLeiXing("xl");
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(YaoPin.class);

        eventListener = new YaoPinEventListener(repository);
    }

    @Test
    public void test_YaoPin_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new YaoPin_ChuangJianCmd(
                        id,
                        "mc",
                        "dl",
                        "xl",
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YaoPin record = getTemplate();

                    record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YaoPin_ChuangJianEvt evt = new YaoPin_ChuangJianEvt(
                id,
                "mc",
                "dl",
                "xl",
                PPUtil.stringToMap("A:1, B:1")
        );

        YaoPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YaoPinView record2 = getViewTemplate();

        record2.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<YaoPinView> captor = ArgumentCaptor.forClass(YaoPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        YaoPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_YaoPin_GengXinCmd() {

        fixture.givenState(() -> {
            YaoPin record = getTemplate();

            return record;
        })
                .when(new YaoPin_GengXinCmd(
                        id,
                        "mc2",
                        "dl2",
                        "xl2",
                        PPUtil.stringToMap("B:2, C:2")

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YaoPin record = getTemplate();

                    record.setMingCheng("mc2");
                    record.setDaLeiXing("dl2");
                    record.setXiaoLeiXing("xl2");
                    record.setXinXiMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YaoPin_GengXinEvt evt = new YaoPin_GengXinEvt(
                id,
                "mc2",
                "dl2",
                "xl2",
                PPUtil.stringToMap("B:2, C:2")
        );

        YaoPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YaoPinView record2 = getViewTemplate();

        record2.setMingCheng("mc2");
        record2.setDaLeiXing("dl2");
        record2.setXiaoLeiXing("xl2");
        record2.setXinXiMap(PPUtil.stringToMap("B:2, C:2"));
        // 修改record到预期结果 end

        ArgumentCaptor<YaoPinView> captor = ArgumentCaptor.forClass(YaoPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        YaoPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_YaoPin_ShangJiaCmd() {

        fixture.givenState(() -> {
            YaoPin record = getTemplate();
            record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new YaoPin_ShangJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YaoPin record = getTemplate();

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YaoPin_ShangJiaEvt evt = new YaoPin_ShangJiaEvt(
                id
        );

        YaoPinView record = getViewTemplate();

        record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YaoPinView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<YaoPinView> captor = ArgumentCaptor.forClass(YaoPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        YaoPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_YaoPin_ShangJiaCmd_失败() {

        fixture.givenState(() -> {
            YaoPin record = getTemplate();

            return record;
        })
                .when(new YaoPin_ShangJiaCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_YaoPin_XiaJiaCmd() {

        fixture.givenState(() -> {
            YaoPin record = getTemplate();

            return record;
        })
                .when(new YaoPin_XiaJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YaoPin record = getTemplate();

                    record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YaoPin_XiaJiaEvt evt = new YaoPin_XiaJiaEvt(
                id
        );

        YaoPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YaoPinView record2 = getViewTemplate();

        record2.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<YaoPinView> captor = ArgumentCaptor.forClass(YaoPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        YaoPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_YaoPin_XiaJiaCmd_失败() {

        fixture.givenState(() -> {
            YaoPin record = getTemplate();
            record.setZhuangTai(YaoPin.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new YaoPin_XiaJiaCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("不在在售状态, 不能下架");
    }

    @Test
    public void test_YaoPin_ShanChuCmd() {

        fixture.givenState(() -> {
            YaoPin record = getTemplate();

            return record;
        })
                .when(new YaoPin_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    YaoPin record = getTemplate();

                    record.delete();
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        YaoPin_ShanChuEvt evt = new YaoPin_ShanChuEvt(
                id
        );

        YaoPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        YaoPinView record2 = getViewTemplate();

        record2.delete();
        // 修改record到预期结果 end

        ArgumentCaptor<YaoPinView> captor = ArgumentCaptor.forClass(YaoPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        YaoPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

}
