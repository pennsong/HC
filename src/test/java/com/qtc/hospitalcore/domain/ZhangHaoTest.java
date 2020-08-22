package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.zhanghao.*;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoEventListener;
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

public class ZhangHaoTest {

    private FixtureConfiguration<ZhangHao> fixture;

    private ZhangHaoViewRepository repository = mock(ZhangHaoViewRepository.class);

    private ZhangHaoEventListener eventListener;

    // mock data
    UUID id = UUID.randomUUID();

    String username = "u";
    String password = "p";
    ZhangHao.JueSe jueSe = ZhangHao.JueSe.YONG_HU;
    Set<ZhangHao.JueSe> jueSeSet = new HashSet<>(Arrays.asList(ZhangHao.JueSe.YONG_HU));
    UUID yongHuId = UUID.randomUUID();
    UUID yiHuRenYuanId = UUID.randomUUID();

    ZhangHao.JueSe jueSe2 = ZhangHao.JueSe.YI_HU_REN_YUAN;
    Set<ZhangHao.JueSe> jueSeSet2 = new HashSet<>(Arrays.asList(ZhangHao.JueSe.YI_HU_REN_YUAN));

    // mock data end

    private ZhangHao getTemplate() {
        ZhangHao template = new ZhangHao();
        template.setId(id);
        template.setUsername(username);
        template.setPassword(password);
        template.setJueSeSet(jueSeSet);
        template.setYongHuId(yongHuId);

        return template;
    }

    private ZhangHaoView getViewTemplate() {
        ZhangHaoView template = new ZhangHaoView();
        template.setId(id);
        template.setUsername(username);
        template.setPassword(password);
        template.setJueSeSet(jueSeSet);
        template.setYongHuId(yongHuId);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ZhangHao.class);

        eventListener = new ZhangHaoEventListener(repository);
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd() {
        // mock data
        ZhangHao_ChuangJianEvt evt = new ZhangHao_ChuangJianEvt(
                id,
                username,
                password,
                jueSe,
                yongHuId,
                null
        );
        // mock data end

        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        id,
                        username,
                        password,
                        jueSe,
                        yongHuId,
                        null
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.setYiHuRenYuanId(null);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);

        verify(repository).saveAndFlush(captor.capture());

        ZhangHaoView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd_2() {
        // mock data
        ZhangHao_ChuangJianEvt evt = new ZhangHao_ChuangJianEvt(
                id,
                username,
                password,
                jueSe2,
                null,
                yiHuRenYuanId
        );
        // mock data end

        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        id,
                        username,
                        password,
                        jueSe2,
                        null,
                        yiHuRenYuanId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.setYongHuId(null);
                    record.setYiHuRenYuanId(yiHuRenYuanId);
                    record.setJueSeSet(jueSeSet2);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();
        record2.setYongHuId(null);
        record2.setJueSeSet(jueSeSet2);
        record2.setYiHuRenYuanId(yiHuRenYuanId);
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);

        verify(repository).saveAndFlush(captor.capture());

        ZhangHaoView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ZhangHao_SheZhiMiMaCmd() {
        // mock data
        String password2 = "p2";

        ZhangHao_SheZhiMiMaEvt evt = new ZhangHao_SheZhiMiMaEvt(
                id,
                password2
        );
        // mock data end

        fixture.givenState(() -> {
            ZhangHao record = getTemplate();

            return record;
        })
                .when(new ZhangHao_SheZhiMiMaCmd(
                        id,
                        password2
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.setPassword(password2);

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ZhangHaoView record = getViewTemplate();
        record.setPassword(password2);

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();
        record2.setPassword(password2);
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);

        verify(repository).saveAndFlush(captor.capture());
        ZhangHaoView result = captor.getValue();

        assertEquals(record2, result);
    }

    @Test
    public void test_ZhangHao_ShanChuCmd() {
        // mock data
        String password2 = "p2";

        ZhangHao_ShanChuEvt evt = new ZhangHao_ShanChuEvt(
                id
        );
        // mock data end

        fixture.givenState(() -> {
            ZhangHao record = getTemplate();

            return record;
        })
                .when(new ZhangHao_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(evt)
                .expectState(state -> {
                    ZhangHao record = getTemplate();
                    record.delete();

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ZhangHaoView record = getViewTemplate();
        record.delete();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();
        record2.delete();
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);
        verify(repository).saveAndFlush(captor.capture());
        ZhangHaoView result = captor.getValue();

        assertEquals(record2, result);
        // deleted是父类属性, 靠assertEquals(record2, result)判断不出来, 所以要单独判断
        assertEquals(record2.isDeleted(), result.isDeleted());
    }
}
