package com.qtc.hospitalcore.domain.zhanghao;

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
public class ZhangHaoViewExtYongHu {
    @Id
    UUID zhangHaoId;

    UUID yongHuId;

    String shouJiHao;
    String xingMing;
    String shenFenZheng;
    String weiXinOpenId;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}