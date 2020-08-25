package com.qtc.hospitalcore.domain.wenzhen;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qtc.hospitalcore.domain.query.PPEntity;
import com.qtc.hospitalcore.domain.util.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WenZhenView extends PPEntity {
    @Id
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID id;

    @Enumerated(EnumType.STRING)
    ZhuangTai zhuangTai;
    String wanChengBeiZhu;

    @Enumerated(EnumType.STRING)
    FuFeiZhuangTai fuFeiZhuangTai;

    OffsetDateTime xiaDanShiJian;
    BigDecimal yuFuFei;
    BigDecimal zongJia;

    // 产品相关
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID chanPinId;
    String chanPinMingCheng;
    String chanPinJsonString;

    // 排班相关
    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID paiBanId;
    String paiBanJsonString;

    // 保险相关
    String baoXianDanHao;
    String xianZhongMingCheng;

    // 健康档案相关
    @Column(columnDefinition = "varchar(128) not null")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID jianKangDangAnId;
    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jianKangDangAnMap;

    // 医护人员相关
    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID wenZhenZhangHaoId;
    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID bingLiBianJiZhangHaoId;
    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID kaiJuChuFangZhangHaoId;
    @Column(columnDefinition = "varchar(128)")
    @org.hibernate.annotations.Type(type = "org.hibernate.type.UUIDCharType")
    UUID queRenChuFangZhangHaoId;

    // 目前主要诊断
    String muQianZhuYaoZhenDuan;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> xiangXiZhiLiaoJingGuoMap;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> jianChaZongJieMap;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> dianZiYingXiangMap;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> qiTaCaiLiaoMap;

    @Column(columnDefinition = "json")
    @Convert(converter = HashMapConverter.class)
    Map<String, Object> wenZhenZongJieMap;

    // 款
    @Embedded
    YuFuKuan yuFuKuan;

    @ElementCollection
    List<BuChongKuan> buChongKuanList = new LinkedList<>();

    @ElementCollection
    List<TuiKuan> tuiKuanList = new LinkedList<>();

    // 会诊
    @Embedded
    HuiZhen huiZhen;

    // 处方
    @Embedded
    ChuFang chuFang;

    // 报告
    @ElementCollection
    List<WenZhenBaoGao> wenZhenBaoGaoList = new LinkedList<>();

    @ElementCollection
    List<ZhenLiaoBaoGao> zhenLiaoBaoGaoList = new LinkedList<>();
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class YuFuKuan {
    String liuShuiHao;
    @Column(name = "yuFuKuanShiJian")
    OffsetDateTime shiJian;
    BigDecimal jinE;
    @Column(name = "yuFuKuanBeiZhu")
    String beiZhu;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class BuChongKuan {
    String liuShuiHao;
    OffsetDateTime shiJian;
    String fuKuanFang;
    BiZhong biZhong;
    BigDecimal jinE;
    double fuKuanDangRiHuiLv;
    String beiZhu;
    String pingZhengList;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class TuiKuan {
    String liuShuiHao;
    OffsetDateTime shiJian;
    String shouKuanZhangHuMing;
    String shouKuanZhangHu;
    BigDecimal jinE;
    String beiZhu;
    String pingZhengList;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class HuiZhen {
    @Column(name = "huiZhenZhuangTai")
    HuiZhenZhuangTai zhuangTai;
    @Column(name = "huiZhenShiJian")
    OffsetDateTime shiJian;
    String lianJie;
    String huiYiId;
    String huanFangCanYuRenYuan;
    @Column(name = "huiZhenBeiZhu")
    String beiZhu;

    String shiPinLianJie;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class WenZhenBaoGao {
    UUID id;
    String zhengWen;
    UUID zhangHaoId;
    OffsetDateTime shiJian;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class ZhenLiaoBaoGao {
    UUID id;
    String zhengWen;
    UUID zhangHaoId;
    OffsetDateTime shiJian;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class ChuFang {
    @Column(name = "chuFangZhuangTai")
    ChuFangZhuangTai zhuangTai;
    String zhengWen;

    UUID kaiJuZhangHaoId;
    OffsetDateTime kaiJuShiJian;

    UUID queRenZhangHaoId;
    OffsetDateTime queRenShiJian;

    UUID quXiaoZhangHaoId;
}