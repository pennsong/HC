package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Data
public class WenZhenViewExtYongHu {
    @Id
    String Id;

    ZhuangTai zhuangTai;
    FuFeiZhuangTai fuFeiZhuangTai;
    String chanPinMingCheng;
    OffsetDateTime xiaDanShiJian;






    String shouJiHao;
    String xingMing;
    String shenFenZheng;
    String weiXinOpenId;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}