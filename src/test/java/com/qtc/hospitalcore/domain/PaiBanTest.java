package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.paiban.*;
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
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaiBanTest {

    private FixtureConfiguration<PaiBan> fixture;

    private PaiBanViewRepository repository = mock(PaiBanViewRepository.class);

    private PaiBanEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();

    UUID chanPinId = UUID.randomUUID();
    // mock data end

    private PaiBan getTemplate() {
        PaiBan template = new PaiBan();

        template.setId(id);
        template.setZhuangTai(ZhuangTai.ZAI_SHOU);
        template.setChanPinId(chanPinId);
        template.setYuFuFei(new BigDecimal(10));
        template.setShiChangJia(new BigDecimal(100));
        template.setYiSheng("ys");
        template.setShiJian(mockNow);
        template.setShouChu(false);
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    private PaiBanView getViewTemplate() {
        PaiBanView template = new PaiBanView();

        template.setId(id);
        template.setZhuangTai(ZhuangTai.ZAI_SHOU);
        template.setChanPinId(chanPinId);
        template.setYuFuFei(new BigDecimal(10));
        template.setShiChangJia(new BigDecimal(100));
        template.setYiSheng("ys");
        template.setShiJian(mockNow);
        template.setShouChu(false);
        template.setXinXiMap(PPUtil.stringToMap("A:1, B:1"));

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(PaiBan.class);

        eventListener = new PaiBanEventListener(repository);
    }

    @Test
    public void test_PaiBan_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new PaiBan_ChuangJianCmd(
                        id,
                        chanPinId,
                        new BigDecimal(10),
                        new BigDecimal(100),
                        "ys",
                        mockNow,
                        PPUtil.stringToMap("A:1, B:1")
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    PaiBan record = getTemplate();

                    record.setZhuangTai(ZhuangTai.TING_SHOU);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        PaiBan_ChuangJianEvt evt = new PaiBan_ChuangJianEvt(
                id,
                chanPinId,
                new BigDecimal(10),
                new BigDecimal(100),
                "ys",
                mockNow,
                PPUtil.stringToMap("A:1, B:1")
        );

        PaiBanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        PaiBanView record2 = getViewTemplate();

        record2.setZhuangTai(ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<PaiBanView> captor = ArgumentCaptor.forClass(PaiBanView.class);
        verify(repository).saveAndFlush(captor.capture());
        PaiBanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_PaiBan_ShangJiaCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setZhuangTai(ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_ShangJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    PaiBan record = getTemplate();

                    record.setZhuangTai(ZhuangTai.ZAI_SHOU);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        PaiBan_ShangJiaEvt evt = new PaiBan_ShangJiaEvt(
                id
        );

        PaiBanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        PaiBanView record2 = getViewTemplate();

        record2.setZhuangTai(ZhuangTai.ZAI_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<PaiBanView> captor = ArgumentCaptor.forClass(PaiBanView.class);
        verify(repository).saveAndFlush(captor.capture());
        PaiBanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_PaiBan_ShangJiaCmd_失败() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

            return record;
        })
                .when(new PaiBan_ShangJiaCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("不在停售状态, 不能上架");
    }

    @Test
    public void test_PaiBan_XiaJiaCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

            return record;
        })
                .when(new PaiBan_XiaJiaCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    PaiBan record = getTemplate();

                    record.setZhuangTai(ZhuangTai.TING_SHOU);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        PaiBan_XiaJiaEvt evt = new PaiBan_XiaJiaEvt(
                id
        );

        PaiBanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        PaiBanView record2 = getViewTemplate();

        record2.setZhuangTai(ZhuangTai.TING_SHOU);
        // 修改record到预期结果 end

        ArgumentCaptor<PaiBanView> captor = ArgumentCaptor.forClass(PaiBanView.class);
        verify(repository).saveAndFlush(captor.capture());
        PaiBanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_PaiBan_XiaJiaCmd_失败() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setZhuangTai(ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_XiaJiaCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("不在在售状态, 不能下架");
    }

    @Test
    public void test_PaiBan_ShouChuCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

            return record;
        })
                .when(new PaiBan_ShouChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    PaiBan record = getTemplate();

                    record.setShouChu(true);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        PaiBan_ShouChuEvt evt = new PaiBan_ShouChuEvt(
                id
        );

        PaiBanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        PaiBanView record2 = getViewTemplate();

        record2.setShouChu(true);
        // 修改record到预期结果 end

        ArgumentCaptor<PaiBanView> captor = ArgumentCaptor.forClass(PaiBanView.class);
        verify(repository).saveAndFlush(captor.capture());
        PaiBanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_PaiBan_ShouChuCmd_失败_1() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setZhuangTai(ZhuangTai.TING_SHOU);

            return record;
        })
                .when(new PaiBan_ShouChuCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("非在售状态, 不能售出");
    }

    @Test
    public void test_PaiBan_ShouChuCmd_失败_2() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();
            record.setShouChu(true);

            return record;
        })
                .when(new PaiBan_ShouChuCmd(
                        id
                ))
                .expectException(PPBusinessException.class)
                .expectExceptionMessage("已售出, 不能再次售出");
    }

    @Test
    public void test_PaiBan_ShanChuCmd() {

        fixture.givenState(() -> {
            PaiBan record = getTemplate();

            return record;
        })
                .when(new PaiBan_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    PaiBan record = getTemplate();

                    record.delete();
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        PaiBan_ShanChuEvt evt = new PaiBan_ShanChuEvt(
                id
        );

        PaiBanView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        PaiBanView record2 = getViewTemplate();

        record2.delete();
        // 修改record到预期结果 end

        ArgumentCaptor<PaiBanView> captor = ArgumentCaptor.forClass(PaiBanView.class);
        verify(repository).saveAndFlush(captor.capture());
        PaiBanView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

}
