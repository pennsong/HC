package com.qtc.hospitalcore.domain.zhanghao;

import com.qtc.hospitalcore.domain.util.HashMapConverter;
import com.qtc.hospitalcore.domain.yihurenyuan.QuanXian;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class ZhangHaoViewExtYiHuRenYuan {
    @Id
    String zhangHaoId;

    String yiHuRenYuanId;

    String xingMing;
    String shenFenZheng;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> quanXianMap;

    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xinXiMap;
}