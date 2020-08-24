package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.util.PPUtil;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChanPinTest {
    private FixtureConfiguration<ChanPin> fixture;

    private ChanPinViewRepository repository = mock(ChanPinViewRepository.class);

    private ChanPinEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();
    // mock data end

    private ChanPin getTemplate() {
        ChanPin template = new ChanPin();

        template.setId(id);
        template.setZhuangTai(ZhuangTai.ZAI_SHOU);
        template.setMingCheng("mc");
        template.setDaLeiXing("dl");
        template.setXiaoLeiXing("xl");
        template.setYuFuFei(new BigDecimal(10));
        template.setShiChangJia(new BigDecimal(100));
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    private ChanPinView getViewTemplate() {
        ChanPinView template = new ChanPinView();

        template.setId(id);
        template.setZhuangTai(ZhuangTai.ZAI_SHOU);
        template.setMingCheng("mc");
        template.setDaLeiXing("dl");
        template.setXiaoLeiXing("xl");
        template.setYuFuFei(new BigDecimal(10));
        template.setShiChangJia(new BigDecimal(100));
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ChanPin.class);

        eventListener = new ChanPinEventListener(repository);
    }

    @Test
    public void test_ChanPin_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new ChanPin_ChuangJianCmd(
                        id,
                        "mc",
                        "dl",
                        "xl",
                        new BigDecimal(10),
                        new BigDecimal(100),
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ChanPin record = getTemplate();

                    record.setZhuangTai(ZhuangTai.TING_SHOU);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPin_ChuangJianEvt evt = new ChanPin_ChuangJianEvt(
                id,
                "mc",
                "dl",
                "xl",
                new BigDecimal(10),
                new BigDecimal(100),
                PPUtil.stringToMap("A:1, B:1")
        );

        ChanPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();

        record2.setZhuangTai(ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        ChanPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ChanPin_GengXinCmd() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_GengXinCmd(
                        id,
                        "mc2",
                        "dl2",
                        "xl2",
                        new BigDecimal(11),
                        new BigDecimal(101),
                        PPUtil.stringToMap("B:2, C:2")

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ChanPin record = getTemplate();

                    record.setMingCheng("mc2");
                    record.setDaLeiXing("dl2");
                    record.setXiaoLeiXing("xl2");
                    record.setYuFuFei( new BigDecimal(11));
                    record.setShiChangJia( new BigDecimal(101));
                    record.setXinXiMap(PPUtil.stringToMap("B:2, C:2"));

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPin_GengXinEvt evt = new ChanPin_GengXinEvt(
                id,
                "mc2",
                "dl2",
                "xl2",
                new BigDecimal(11),
                new BigDecimal(101),
                PPUtil.stringToMap("B:2, C:2")
        );

        ChanPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();

        record2.setMingCheng("mc2");
        record2.setDaLeiXing("dl2");
        record2.setXiaoLeiXing("xl2");
        record2.setYuFuFei( new BigDecimal(11));
        record2.setShiChangJia( new BigDecimal(101));
        record2.setXinXiMap(PPUtil.stringToMap("B:2, C:2"));
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        ChanPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ChanPin_ShangJiaCmd() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();
            record.setZhuangTai(ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new ChanPin_ShangJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ChanPin record = getTemplate();

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPin_ShangJiaEvt evt = new ChanPin_ShangJiaEvt(
                id
        );

        ChanPinView record = getViewTemplate();

        record.setZhuangTai(ZhuangTai.TING_SHOU);

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        ChanPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ChanPin_ShangJiaCmd_失败() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_ShangJiaCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_ChanPin_XiaJiaCmd() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_XiaJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ChanPin record = getTemplate();

                    record.setZhuangTai(ZhuangTai.TING_SHOU);
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPin_XiaJiaEvt evt = new ChanPin_XiaJiaEvt(
                id
        );

        ChanPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();

        record2.setZhuangTai(ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        ChanPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ChanPin_XiaJiaCmd_失败() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();
            record.setZhuangTai(ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new ChanPin_XiaJiaCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("不在在售状态, 不能下架");
    }

    @Test
    public void test_ChanPin_ShanChuCmd() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ChanPin record = getTemplate();

                    record.delete();
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPin_ShanChuEvt evt = new ChanPin_ShanChuEvt(
                id
        );

        ChanPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();

        record2.delete();
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);
        verify(repository).saveAndFlush(captor.capture());
        ChanPinView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

}
