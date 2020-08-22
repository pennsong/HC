package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chanpin.*;
import com.qtc.hospitalcore.domain.chanpin.ChanPinEventListener;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChanPinTest {
    private FixtureConfiguration<ChanPin> fixture;

    private ChanPinViewRepository repository = mock(ChanPinViewRepository.class);

    private ChanPinEventListener eventListener;
    // mock data
    UUID id = UUID.randomUUID();

    String mingCheng = "c";
    String daLeiXing = "d";
    String xiaoLeiXing = "x";
    BigDecimal yuFuFei = new BigDecimal("1.1");
    BigDecimal shiChangJia = new BigDecimal("10.1");
    Map<String, Object> xinXiMap = PPUtil.stringToMap("A:1, B:1");

    // mock data end

    private ChanPin getTemplate() {
        ChanPin template = new ChanPin();
        template.setId(id);
        template.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);
        template.setMingCheng(mingCheng);
        template.setDaLeiXing(daLeiXing);
        template.setXiaoLeiXing(xiaoLeiXing);
        template.setYuFuFei(yuFuFei);
        template.setShiChangJia(shiChangJia);
        template.setXinXiMap(xinXiMap);

        return template;
    }

    private ChanPinView getViewTemplate() {
        ChanPinView template = new ChanPinView();
        template.setId(id);
        template.setZhuangTai(ChanPin.ZhuangTai.ZAI_SHOU);
        template.setMingCheng(mingCheng);
        template.setDaLeiXing(daLeiXing);
        template.setXiaoLeiXing(xiaoLeiXing);
        template.setYuFuFei(yuFuFei);
        template.setShiChangJia(shiChangJia);
        template.setXinXiMap(xinXiMap);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ChanPin.class);

        eventListener = new ChanPinEventListener(repository);
    }

    @Test
    public void test_ChanPin_ChuangJianCmd() {
        // mock data
        ChanPin_ChuangJianEvt evt = new ChanPin_ChuangJianEvt(
                id,
                mingCheng,
                daLeiXing,
                xiaoLeiXing,
                yuFuFei,
                shiChangJia,
                xinXiMap
        );
        // mock data end

        fixture.givenNoPriorActivity()
                .when(new ChanPin_ChuangJianCmd(
                        id,
                        mingCheng,
                        daLeiXing,
                        xiaoLeiXing,
                        yuFuFei,
                        shiChangJia,
                        xinXiMap
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChanPin record = getTemplate();
                    record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();
        record2.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);

        verify(repository).saveAndFlush(captor.capture());

        ChanPinView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ChanPin_GengXinCmd() {
        // mock data
        String mingCheng2 = "c2";
        String daLeiXing2 = "d2";
        String xiaoLeiXing2 = "x2";
        BigDecimal yuFuFei2 = new BigDecimal("2.2");
        BigDecimal shiChangJia2 = new BigDecimal("20.1");
        Map<String, Object> xinXiMap2 = PPUtil.stringToMap("B:2, C:2");

        ChanPin_GengXinEvt evt = new ChanPin_GengXinEvt(
                id,
                mingCheng2,
                daLeiXing2,
                xiaoLeiXing2,
                yuFuFei2,
                shiChangJia2,
                xinXiMap2
        );
        // mock data end

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_GengXinCmd(
                        id,
                        mingCheng2,
                        daLeiXing2,
                        xiaoLeiXing2,
                        yuFuFei2,
                        shiChangJia2,
                        xinXiMap2

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChanPin record = getTemplate();
                    record.setMingCheng(mingCheng2);
                    record.setDaLeiXing(daLeiXing2);
                    record.setXiaoLeiXing(xiaoLeiXing2);
                    record.setYuFuFei(yuFuFei2);
                    record.setShiChangJia(shiChangJia2);
                    record.setXinXiMap(xinXiMap2);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();
        record2.setMingCheng(mingCheng2);
        record2.setDaLeiXing(daLeiXing2);
        record2.setXiaoLeiXing(xiaoLeiXing2);
        record2.setYuFuFei(yuFuFei2);
        record2.setShiChangJia(shiChangJia2);
        record2.setXinXiMap(xinXiMap2);
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);

        verify(repository).saveAndFlush(captor.capture());

        ChanPinView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ChanPin_ShangJiaCmd() {
        // mock data
        ChanPin_ShangJiaEvt evt = new ChanPin_ShangJiaEvt(
                id
        );
        // mock data end

        fixture.givenState(() -> {
            ChanPin record = getTemplate();
            record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new ChanPin_ShangJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChanPin record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPinView record = getViewTemplate();
        record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);

        verify(repository).saveAndFlush(captor.capture());
        ChanPinView result = captor.getValue();

        assertEquals(record2, result);
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
        // mock data
        ChanPin_XiaJiaEvt evt = new ChanPin_XiaJiaEvt(
                id
        );
        // mock data end

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_XiaJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChanPin_XiaJiaEvt(
                        id
                ))
                .expectState(state -> {
                    ChanPin record = getTemplate();
                    record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChanPinView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChanPinView record2 = getViewTemplate();
        record2.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<ChanPinView> captor = ArgumentCaptor.forClass(ChanPinView.class);

        verify(repository).saveAndFlush(captor.capture());
        ChanPinView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ChanPin_XiaJiaCmd_失败() {

        fixture.givenState(() -> {
            ChanPin record = getTemplate();
            record.setZhuangTai(ChanPin.ZhuangTai.TING_SHOU);

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
        // mock data
        ChanPin_ShanChuEvt evt = new ChanPin_ShanChuEvt(
                id
        );
        // mock data end

        fixture.givenState(() -> {
            ChanPin record = getTemplate();

            return record;
        })
                .when(new ChanPin_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChanPin record = getTemplate();
                    record.delete();

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
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
        ChanPinView result = captor.getValue();

        assertEquals(record2, result);
        // deleted是父类属性, 靠assertEquals(record2, result)判断不出来, 所以要单独判断
        assertEquals(record2.isDeleted(), result.isDeleted());
    }

}
