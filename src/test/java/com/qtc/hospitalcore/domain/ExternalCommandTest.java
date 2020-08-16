package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.PPUtil;
import com.qtc.hospitalcore.domain.yonghu.*;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExternalCommandTest {
    private FixtureConfiguration<YongHu> yongHuFixtureConfiguration;

    private FixtureConfiguration<ZhangHao> zhangHaoTestFixtureConfiguration;

    @BeforeEach
    public void setUp() {

    }


}
