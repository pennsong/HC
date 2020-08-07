package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import com.sun.scenario.effect.Offset;
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

import javax.persistence.Convert;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class YongHu {

    @AggregateIdentifier
    UUID id;

    String openId;
    String shouJiHao;
    String yanZhengMa;
    OffsetDateTime yanZhengMaDeadline;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}
