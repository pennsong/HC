package com.qtc.hospitalcore.domain.yaopin;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class YaoPinView {
    @Id
    UUID id;

    String yaoPinMing;
    String daLeiXing;
    String xiaoLeiXing;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXi;
}