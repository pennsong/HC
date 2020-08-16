package com.qtc.hospitalcore.domain.chanpin;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class ChanPinView {

    @Id
    UUID id;

    String chanPinMing;
    String daLeiXing;
    String xiaoLeiXing;

    BigDecimal yuFuFei;
    BigDecimal shiChangJia;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}
