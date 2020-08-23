package com.qtc.hospitalcore.domain.chufang;

import com.qtc.hospitalcore.domain.chanpin.ChanPin;
import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
public class ChuFangView extends PPEntity {

    @Id
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID wenZhenId;

    ChuFang.ZhuangTai zhuangTai;
    String zhengWen;

    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID kaiJuZhangHaoId;
    OffsetDateTime kaiJuShiJian;

    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID queRenZhangHaoId;
    OffsetDateTime queRenShiJian;

    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID quXiaoZhangHaoId;
}
