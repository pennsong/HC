package com.qtc.hospitalcore.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Set;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ZhangHao {
    public static enum JueSe {
        YONG_HU,
        YI_HU_REN_YUAN
    }

    @AggregateIdentifier
    UUID id;
    String username;
    String password;
    Set<JueSe> jueSeSet;
}
