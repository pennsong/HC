package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yaopin.YaoPin;
import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_GengXinEvt;
import com.qtc.hospitalcore.domain.yaopin.YaoPin_ShanChuEvt;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.yonghu.YongHuEventListener;
import com.qtc.hospitalcore.domain.yonghu.YongHuView;
import com.qtc.hospitalcore.domain.yonghu.YongHuViewRepository;
import com.qtc.hospitalcore.domain.zhanghao.*;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoEventListener;
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

public class ZhangHaoTest {

    private FixtureConfiguration<ZhangHao> fixture;

    private ZhangHaoViewRepository repository = mock(ZhangHaoViewRepository.class);

    private ZhangHaoEventListener eventListener;

    // mock data
    // mock now
    OffsetDateTime mockNow = OffsetDateTime.now();
    OffsetDateTime nullTime = OffsetDateTime.of(3000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);

    UUID id = UUID.randomUUID();
    UUID yongHuId = UUID.randomUUID();
    UUID yiHuRenYuanId = UUID.randomUUID();
    // mock data end

    private ZhangHao getTemplate() {
        ZhangHao template = new ZhangHao();

        template.setId(id);
        template.setUsername("un");
        template.setPassword("pw");
        template.setJueSeSet(
                new HashSet<>(
                        Arrays.asList(
                                JueSe.YONG_HU
                        )
                )
        );

        return template;
    }

    private ZhangHaoView getViewTemplate() {
        ZhangHaoView template = new ZhangHaoView();

        template.setId(id);
        template.setUsername("un");
        template.setPassword("pw");
        template.setJueSeSet(
                new HashSet<>(
                        Arrays.asList(
                                JueSe.YONG_HU
                        )
                )
        );

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ZhangHao.class);

        eventListener = new ZhangHaoEventListener(repository);
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd() {

        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        id,
                        "un",
                        "pw",
                        JueSe.YONG_HU,
                        yongHuId,
                        null
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ZhangHao record = getTemplate();

                    record.setYongHuId(yongHuId);

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ZhangHao_ChuangJianEvt evt = new ZhangHao_ChuangJianEvt(
                id,
                "un",
                "pw",
                JueSe.YONG_HU,
                yongHuId,
                null
        );

        ZhangHaoView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();
        record2.setYongHuId(yongHuId);
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);
        verify(repository).saveAndFlush(captor.capture());
        ZhangHaoView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ZhangHao_ChuangJianCmd_2() {
        fixture.givenNoPriorActivity()
                .when(new ZhangHao_ChuangJianCmd(
                        id,
                        "un",
                        "pw",
                        JueSe.YI_HU_REN_YUAN,
                        null,
                        yiHuRenYuanId
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ZhangHao record = getTemplate();

                    record.setJueSeSet(
                            new HashSet<>(
                                    Arrays.asList(
                                            JueSe.YI_HU_REN_YUAN
                                    )    
                            )
                    );
                    record.setYongHuId(null);
                    record.setYiHuRenYuanId(yiHuRenYuanId);
                    
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ZhangHao_ChuangJianEvt evt = new ZhangHao_ChuangJianEvt(
                id,
                "un",
                "pw",
                JueSe.YI_HU_REN_YUAN,
                null,
                yiHuRenYuanId
        );

        ZhangHaoView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();

        record2.setJueSeSet(
                new HashSet<>(
                        Arrays.asList(
                                JueSe.YI_HU_REN_YUAN
                        )
                )
        );
        record2.setYongHuId(null);
        record2.setYiHuRenYuanId(yiHuRenYuanId);
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);
        verify(repository).saveAndFlush(captor.capture());
        ZhangHaoView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ZhangHao_SheZhiMiMaCmd() {

        fixture.givenState(() -> {
            ZhangHao record = getTemplate();

            return record;
        })
                .when(new ZhangHao_SheZhiMiMaCmd(
                        id,
                        "pw2"
                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ZhangHao record = getTemplate();

                    record.setPassword("pw2");

                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ZhangHao_SheZhiMiMaEvt evt = new ZhangHao_SheZhiMiMaEvt(
                id,
                "pw2"
        );

        ZhangHaoView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();

       record2.setPassword("pw2");
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);
        verify(repository).saveAndFlush(captor.capture());
        ZhangHaoView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }

    @Test
    public void test_ZhangHao_ShanChuCmd() {
      
        fixture.givenState(() -> {
            ZhangHao record = getTemplate();

            return record;
        })
                .when(new ZhangHao_ShanChuCmd(
                        id

                ))
                .expectSuccessfulHandlerExecution()
                .expectState(state -> {
                    ZhangHao record = getTemplate();

                    record.delete();
                    // 时间相关assertions
                    // 时间相关assertions end

                    // 调整时间相关field准备整体比较
                    // 调整时间相关field准备整体比较 end

                    // perform assertions
                    assertEquals(record, state);
                });

        // query model update
        ZhangHao_ShanChuEvt evt = new ZhangHao_ShanChuEvt(
                id
        );

        ZhangHaoView record = getViewTemplate();

        Mockito.when(repository.findById(evt.getId()))
                .thenReturn(Optional.of(record));

        eventListener.on(evt);

        // 修改record到预期结果
        ZhangHaoView record2 = getViewTemplate();

        record2.delete();
        // 修改record到预期结果 end

        ArgumentCaptor<ZhangHaoView> captor = ArgumentCaptor.forClass(ZhangHaoView.class);
        verify(repository).saveAndFlush(captor.capture());
        ZhangHaoView state = captor.getValue();

        // 时间相关assertions
        // 时间相关assertions end

        // 调整时间相关field准备整体比较
        // 调整时间相关field准备整体比较 end

        assertEquals(record2, state);
    }
}
