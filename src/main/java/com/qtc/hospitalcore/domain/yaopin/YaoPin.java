package com.qtc.hospitalcore.domain.yaopin;

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
import java.util.Map;
import java.util.UUID;

@Slf4j
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class YaoPin {

    @AggregateIdentifier
    UUID id;

    String yaoPinMing;
    String daLeiXing;
    String xiaoLeiXing;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}
