package com.qtc.hospitalcore;

import com.qtc.hospitalcore.domain.ZhangHao;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    ExternalHandler externalCommandHandler;

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String...args) throws Exception {
        log.info("Application started");
        externalCommandHandler.setZhangHaoRepository((Repository<ZhangHao>) context.getBean("zhangHaoRepository"));

//        String[] beans = context.getBeanDefinitionNames();
//        Arrays.sort(beans);
//        for (String bean : beans) {
//            System.out.println(bean);
//        }

    }
}
