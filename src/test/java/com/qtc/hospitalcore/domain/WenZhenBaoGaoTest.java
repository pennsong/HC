package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chufang.*;
import com.qtc.hospitalcore.domain.wenzhenbaogao.WenZhenBaoGao;
import com.qtc.hospitalcore.domain.wenzhenbaogao.WenZhenBaoGao_KaiJuCmd;
import com.qtc.hospitalcore.domain.wenzhenbaogao.WenZhenBaoGao_KaiJuEvt;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WenZhenBaoGaoTest {
    private FixtureConfiguration<WenZhenBaoGao> fixture;

    // mock data
    LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    
    UUID id = UUID.randomUUID();
    UUID wenZhenId = UUID.randomUUID();

    String zhengWen = "z";
    UUID kaiJuZhangHaoId = UUID.randomUUID();
    LocalDateTime kaiJuShiJian = mockNow;

    // mock data end

    private WenZhenBaoGao getTemplate() {
        WenZhenBaoGao template = new WenZhenBaoGao();
        template.setId(id);
        template.setWenZhenId(wenZhenId);
        template.setZhengWen(zhengWen);
        template.setKaiJuZhangHaoId(kaiJuZhangHaoId);
        template.setKaiJuShiJian(kaiJuShiJian);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(WenZhenBaoGao.class);
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_WenZhenBaoGao_KaiJuCmd() {

        fixture.givenNoPriorActivity()
                .when(new WenZhenBaoGao_KaiJuCmd(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new WenZhenBaoGao_KaiJuEvt(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId,
                        mockNow
                ))
                .expectState(state -> {
                    WenZhenBaoGao record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }
}
