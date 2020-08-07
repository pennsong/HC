package com.qtc.hospitalcore.domain;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Convert;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ChangPin {

    @AggregateIdentifier
    UUID id;

    String fuWuDaFenLei;
    String fuWuLeiXing;
    String chanPingMing;
    BigDecimal yuFuFei;
    BigDecimal shiChangJia;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}
