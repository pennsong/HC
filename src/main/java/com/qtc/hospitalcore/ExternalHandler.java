package com.qtc.hospitalcore;

import com.qtc.hospitalcore.domain.*;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalHandler {
    @Setter
    private Repository<ZhangHao> zhangHaoRepository;

}
