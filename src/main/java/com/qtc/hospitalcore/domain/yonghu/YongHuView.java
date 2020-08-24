package com.qtc.hospitalcore.domain.yonghu;

import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YongHuView extends PPEntity {
    @Id
    @Column(columnDefinition="varchar(128) not null")
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    UUID id;

    String shouJiHao;
    String xingMing;
    String shenFenZheng;
    String weiXinOpenId;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}