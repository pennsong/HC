package com.qtc.hospitalcore.domain.jiankangdangan;

import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JianKangDangAnView extends PPEntity {
    @Id
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID yongHuId;

    ZhuangTai zhuangTai;

    String xingMing;
    String shenFenZhengHao;
    String shouJiHao;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jiBenXinXiMap;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jianKangXinXiMap;
}