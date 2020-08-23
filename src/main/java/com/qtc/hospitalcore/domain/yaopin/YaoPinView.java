package com.qtc.hospitalcore.domain.yaopin;

import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YaoPinView extends PPEntity {
    @Id
    UUID id;

    YaoPin.ZhuangTai zhuangTai;

    String mingCheng;
    String daLeiXing;
    String xiaoLeiXing;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}