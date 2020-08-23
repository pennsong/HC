package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chufang.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuFangTest {
    private FixtureConfiguration<ChuFang> fixture;

    private ChuFangViewRepository repository = mock(ChuFangViewRepository.class);

    private ChuFangEventListener eventListener;
    // mock data
    OffsetDateTime mockNow = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    
    UUID id = UUID.randomUUID();
    UUID wenZhenId = UUID.randomUUID();

    ChuFang.ZhuangTai zhuangTai = ChuFang.ZhuangTai.YI_KAI_JU;
    String zhengWen = "z";
    UUID kaiJuZhangHaoId = UUID.randomUUID();
    OffsetDateTime kaiJuShiJian = mockNow;

    UUID queRenZhangHaoId = UUID.randomUUID();
    OffsetDateTime queRenShiJian = mockNow;

    UUID quXiaoZhangHaoId = UUID.randomUUID();

    // mock data end

    private ChuFang getTemplate() {
        ChuFang template = new ChuFang();
        template.setId(id);
        template.setWenZhenId(wenZhenId);
        template.setZhuangTai(zhuangTai);
        template.setZhengWen(zhengWen);
        template.setKaiJuZhangHaoId(kaiJuZhangHaoId);
        template.setKaiJuShiJian(kaiJuShiJian);

        return template;
    }

    private ChuFangView getViewTemplate() {
        ChuFangView template = new ChuFangView();
        template.setId(id);
        template.setWenZhenId(wenZhenId);
        template.setZhuangTai(zhuangTai);
        template.setZhengWen(zhengWen);
        template.setKaiJuZhangHaoId(kaiJuZhangHaoId);
        template.setKaiJuShiJian(kaiJuShiJian);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ChuFang.class);

        eventListener = new ChuFangEventListener(repository);
    }

    // 在时间整分附件测试可能会失败和OffsetDateTime mockNow = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_ChuFang_KaiJuCmd() {
    // mock data
        ChuFang_KaiJuEvt evt = new ChuFang_KaiJuEvt(
                id,
                wenZhenId,
                zhengWen,
                kaiJuZhangHaoId,
                mockNow
        );
        // mock data end

        fixture.givenNoPriorActivity()
                .when(new ChuFang_KaiJuCmd(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChuFang record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        eventListener.on(evt);

        // 修改record到预期结果
        ChuFangView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<ChuFangView> captor = ArgumentCaptor.forClass(ChuFangView.class);

        verify(repository).saveAndFlush(captor.capture());

        ChuFangView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ChuFang_QueRenCmd() {
        // mock data
        ChuFang_QueRenEvt evt = new ChuFang_QueRenEvt(
                id,
                queRenZhangHaoId,
                queRenShiJian
        );
        // mock data end

        fixture.givenState(() -> {
            ChuFang record = getTemplate();

            return record;
        })
                .when(new ChuFang_QueRenCmd(
                        id,
                        queRenZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChuFang record = getTemplate();
                    record.setZhuangTai(ChuFang.ZhuangTai.YI_QUE_REN);
                    record.setQueRenZhangHaoId(queRenZhangHaoId);
                    record.setQueRenShiJian(queRenShiJian);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChuFangView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChuFangView record2 = getViewTemplate();
        record2.setZhuangTai(ChuFang.ZhuangTai.YI_QUE_REN);
        record2.setQueRenZhangHaoId(queRenZhangHaoId);
        record2.setQueRenShiJian(queRenShiJian);

        // 修改record到预期结果 end

        ArgumentCaptor<ChuFangView> captor = ArgumentCaptor.forClass(ChuFangView.class);

        verify(repository).saveAndFlush(captor.capture());
        ChuFangView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ChuFang_QuXiaoCmd() {
        // mock data
        ChuFang_QuXiaoEvt evt = new ChuFang_QuXiaoEvt(
                id,
                quXiaoZhangHaoId
        );
        // mock data end

        fixture.givenState(() -> {
            ChuFang record = getTemplate();

            return record;
        })
                .when(new ChuFang_QuXiaoCmd(
                        id,
                        quXiaoZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ChuFang record = getTemplate();
                    record.setQuXiaoZhangHaoId(quXiaoZhangHaoId);
                    record.setZhuangTai(ChuFang.ZhuangTai.YI_QU_XIAO);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ChuFangView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ChuFangView record2 = getViewTemplate();
        record2.setZhuangTai(ChuFang.ZhuangTai.YI_QU_XIAO);
        record2.setQuXiaoZhangHaoId(quXiaoZhangHaoId);
        // 修改record到预期结果 end

        ArgumentCaptor<ChuFangView> captor = ArgumentCaptor.forClass(ChuFangView.class);

        verify(repository).saveAndFlush(captor.capture());
        ChuFangView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ChuFang_QuXiaoCmd_2() {

        fixture.givenState(() -> {
            ChuFang record = getTemplate();
            record.setQueRenZhangHaoId(queRenZhangHaoId);
            record.setQueRenShiJian(queRenShiJian);
            record.setZhuangTai(ChuFang.ZhuangTai.YI_QUE_REN);

            return record;
        })
                .when(new ChuFang_QuXiaoCmd(
                        id,
                        quXiaoZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChuFang_QuXiaoEvt(
                        id,
                        quXiaoZhangHaoId
                ))
                .expectState(state -> {
                    ChuFang record = getTemplate();
                    record.setQueRenZhangHaoId(queRenZhangHaoId);
                    record.setQueRenShiJian(queRenShiJian);
                    record.setZhuangTai(ChuFang.ZhuangTai.YI_QUE_REN);

                    record.setQuXiaoZhangHaoId(quXiaoZhangHaoId);
                    record.setZhuangTai(ChuFang.ZhuangTai.YI_QU_XIAO);

                    // perform assertions
                    assertEquals(record, state);
                });
    }


}
