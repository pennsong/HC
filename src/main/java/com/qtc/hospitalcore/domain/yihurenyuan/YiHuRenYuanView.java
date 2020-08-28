package com.qtc.hospitalcore.domain.yihurenyuan;

import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YiHuRenYuanView extends PPEntity {
    @Id
    @Column(columnDefinition="varchar(128) not null")
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    UUID id;

    String xingMing;

    String shenFenZheng;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<QuanXian, Boolean> quanXianMap;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}