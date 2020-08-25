/*
 * Copyright (c) 2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qtc.hospitalcore;

import com.qtc.hospitalcore.domain.chanpin.ChanPin;
import com.qtc.hospitalcore.domain.paiban.PaiBan;
import com.qtc.hospitalcore.domain.wenzhen.WenZhen;
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan;
import com.qtc.hospitalcore.domain.yonghu.YongHu;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHao;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    private AxonConfiguration axonConfiguration;

    @Bean
    public Repository<ZhangHao> zhangHaoRepository2() {
        return axonConfiguration.repository(ZhangHao.class);
    }

    @Bean
    public Repository<YongHu> yongHuRepository2() {
        return axonConfiguration.repository(YongHu.class);
    }

    @Bean
    public Repository<YiHuRenYuan> yiHuRenYuanRepository2() {
        return axonConfiguration.repository(YiHuRenYuan.class);
    }

    @Bean
    public Repository<ChanPin> chanPinRepository2() {
        return axonConfiguration.repository(ChanPin.class);
    }

    @Bean
    public Repository<PaiBan> paiBanRepository2() {
        return axonConfiguration.repository(PaiBan.class);
    }

    @Bean
    public Repository<WenZhen> wenZhenRepository2() {
        return axonConfiguration.repository(WenZhen.class);
    }
}
