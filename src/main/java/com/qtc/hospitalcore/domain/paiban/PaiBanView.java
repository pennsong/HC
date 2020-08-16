package com.qtc.hospitalcore.domain.paiban;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class PaiBanView {

    @Id
    UUID id;

    UUID chanPinId;

    BigDecimal yuFuFei;
    BigDecimal shiChangJia;

    String yiSheng;

    // 到小时
    OffsetDateTime shiJian;

    // 售出
    boolean shouChu;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}
