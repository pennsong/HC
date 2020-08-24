package com.qtc.hospitalcore.domain.paiban;

import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaiBanView extends PPEntity {

    @Id
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    ZhuangTai zhuangTai;

    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID chanPinId;

    BigDecimal yuFuFei;
    BigDecimal shiChangJia;

    String yiSheng;

    // 到小时
    OffsetDateTime shiJian;

    // 售出
    boolean shouChu;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}
