package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.ExternalHandler;
import com.qtc.hospitalcore.domain.chanpin.ChanPin;
import com.qtc.hospitalcore.domain.paiban.PaiBan;
import com.qtc.hospitalcore.domain.paiban.PaiBan_ChuangJianEvt;
import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yonghu.*;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import org.aspectj.lang.annotation.Before;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


// 需要mock的东西比较多, 需要权衡
public class ExternalCommandTest {
//    // mock data
//    OffsetDateTime mockNow = OffsetDateTime.now();
//    // mock data end
//
//    @BeforeEach
//    public void setUp() {
//    }
//
//    @Test
//    public void testFirstFixture() {
//        FixtureConfiguration fixture = new AggregateTestFixture(PaiBan.class);
//        fixture.registerAnnotatedCommandHandler(new ExternalHandler());
//
//        UUID id = UUID.randomUUID();
//        UUID chanPinId = UUID.randomUUID();
//
//        fixture.givenNoPriorActivity()
//                .when(new ExtChuangJianPaiBanCmd(
//                        id,
//                        chanPinId,
//                        new BigDecimal(10),
//                        new BigDecimal(100),
//                        "ys",
//                        mockNow,
//                        PPUtil.stringToMap("A:1, B:1")
//
//                ))
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new PaiBan_ChuangJianEvt(
//                        id,
//                        chanPinId,
//                        new BigDecimal(10),
//                        new BigDecimal(100),
//                        "ys",
//                        mockNow,
//                        PPUtil.stringToMap("A:1, B:1")
//                ));
//    }

}
