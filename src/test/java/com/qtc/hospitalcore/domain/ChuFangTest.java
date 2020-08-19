package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.chufang.*;
import com.qtc.hospitalcore.domain.util.PPUtil;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChuFangTest {
    private FixtureConfiguration<ChuFang> fixture;

    // mock data
    LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    
    UUID id = UUID.randomUUID();
    UUID wenZhenId = UUID.randomUUID();

    ChuFang.ZhuangTai zhuangTai = ChuFang.ZhuangTai.YI_KAI_JU;
    String zhengWen = "z";
    UUID kaiJuZhangHaoId = UUID.randomUUID();
    LocalDateTime kaiJuShiJian = mockNow;

    UUID queRenZhangHaoId = UUID.randomUUID();
    LocalDateTime queRenShiJian = mockNow;

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

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(ChuFang.class);
    }

    // 在时间整分附件测试可能会失败和LocalDateTime mockNow = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)有关
    @Test
    public void test_ChuFang_KaiJuCmd() {

        fixture.givenNoPriorActivity()
                .when(new ChuFang_KaiJuCmd(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChuFang_KaiJuEvt(
                        id,
                        wenZhenId,
                        zhengWen,
                        kaiJuZhangHaoId,
                        mockNow
                ))
                .expectState(state -> {
                    ChuFang record = getTemplate();

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ChuFang_QueRenCmd() {

        fixture.givenState(() -> {
            ChuFang record = getTemplate();

            return record;
        })
                .when(new ChuFang_QueRenCmd(
                        id,
                        queRenZhangHaoId
                ))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ChuFang_QueRenEvt(
                        id,
                        queRenZhangHaoId,
                        queRenShiJian
                ))
                .expectState(state -> {
                    ChuFang record = getTemplate();
                    record.setZhuangTai(ChuFang.ZhuangTai.YI_QUE_REN);
                    record.setQueRenZhangHaoId(queRenZhangHaoId);
                    record.setQueRenShiJian(queRenShiJian);

                    // perform assertions
                    assertEquals(record, state);
                });
    }

    @Test
    public void test_ChuFang_QuXiaoCmd_1() {

        fixture.givenState(() -> {
            ChuFang record = getTemplate();

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
                    record.setQuXiaoZhangHaoId(quXiaoZhangHaoId);
                    record.setZhuangTai(ChuFang.ZhuangTai.YI_QU_XIAO);

                    // perform assertions
                    assertEquals(record, state);
                });
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
