package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.zhenliaobaogao.ZhenLiaoBaoGao;
import com.qtc.hospitalcore.domain.zhenliaobaogao.ZhenLiaoBaoGao_KaiJuCmd;
import com.qtc.hospitalcore.domain.zhenliaobaogao.ZhenLiaoBaoGao_KaiJuEvt;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZhenLiaoBaoGaoTest {
    private FixtureConfiguration<ZhenLiaoBaoGao> fixture;

    // mock data
    LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    
    UUID id = UUID.randomUUID();
    UUID wenZhenId = UUID.randomUUID();

    String zhengWen = "z";
    UUID kaiJuZhangHaoId = UUID.randomUUID();
    LocalDateTime kaiJuShiJian = mockNow;

    // mock data end

    private ZhenLiaoBaoGao getTemplate() {
        ZhenLiaoBaoGao template = new ZhenLiaoBaoGao();
        template.setId(id);
        template.setWenZhenId(wenZhenId);
        template.setZhengWen(zhengWen);
        template.setKaiJuZhangHaoId(kaiJuZhangHaoId);
        template.setKaiJuShiJian(kaiJuShiJian);

        return template;
    }

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ZhenLiaoBaoGao.class);
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_ZhenLiaoBaoGao_KaiJuCmd() {

        fixture.givenNoPriorActivity()
                .when(new ZhenLiaoBaoGao_KaiJuCmd(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ZhenLiaoBaoGao_KaiJuEvt(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId,
                        mockNow
                ))
                .expectState(state -> {
                    ZhenLiaoBaoGao record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }
}
