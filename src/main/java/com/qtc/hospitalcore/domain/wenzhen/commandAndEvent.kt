package com.qtc.hospitalcore.domain.wenzhen

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class WenZhen_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var jianKangDangAnId: UUID,
        var chanPinId: UUID,
        var paiBanId: UUID?,
        var yuFuFei: BigDecimal,
        var zongJia: BigDecimal,

        // 产品相关
        var chanPinMingCheng: String,
        var chanPinJsonString: String,
        var paiBanJsonString: String?,
        // 健康档案相关
        var jianKangDangAnMap: Map<String, Any>
)

data class WenZhen_ChuangJianEvt(
        var id: UUID,
        var jianKangDangAnId: UUID,
        var chanPinId: UUID,
        var paiBanId: UUID?,
        var yuFuFei: BigDecimal,
        var zongJia: BigDecimal,

        // 产品相关
        var chanPinMingCheng: String,
        var chanPinJsonString: String,
        var paiBanJsonString: String?,
        // 健康档案相关
        var jianKangDangAnMap: Map<String, Any>,

        var xiaDanShiJian: LocalDateTime
)

data class WenZhen_ZhiFuYuFuKuanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var liuShuiHao: String,
        var beiZhu: String,
        var jinE: BigDecimal
)

data class WenZhen_ZhiFuYuFuKuanEvt(
        var id: UUID,
        var liuShuiHao: String,
        var beiZhu: String,
        var jinE: BigDecimal,

        var shiJian: LocalDateTime
)

data class WenZhen_ZhiXingTuiKuanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: LocalDateTime,
        var shouKuanZhangHuMing: String,
        var shouKuanZhangHu: String,
        var jinE: BigDecimal,
        var beiZhu: String?,
        var pingZheng: List<String>
)

data class WenZhen_ZhiXingTuiKuanEvt(
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: LocalDateTime,
        var shouKuanZhangHuMing: String,
        var shouKuanZhangHu: String,
        var jinE: BigDecimal,
        var beiZhu: String?,
        var pingZheng: List<String>
)

data class WenZhen_GengXinJianKangDangAnCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var jianKangDangAnMap: Map<String, Any>
)

data class WenZhen_GengXinJianKangDangAnEvt(
        var id: UUID,
        var jianKangDangAnMap: Map<String, Any>
)

data class WenZhen_ZhiFuBuChongKuanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: LocalDateTime,
        var fuKuanFang: String,
        var biZhong: String,
        var jinE: BigDecimal,
        var fuKuanDangRiHuiLv: Double,
        var beiZhu: String?,
        var pingZheng: List<String>
)

data class WenZhen_ZhiFuBuChongKuanEvt(
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: LocalDateTime,
        var fuKuanFang: String,
        var biZhong: String,
        var jinE: BigDecimal,
        var fuKuanDangRiHuiLv: Double,
        var beiZhu: String?,
        var pingZheng: List<String>
)

// 由WenZhen_ZhiFuBuChongKuanCmd触发
data class WenZhen_ZhiFuQuanKuanEvt(
        var id: UUID
)

data class WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhenDuan: String
)

data class WenZhen_GengXinMuQianZhuYaoZhenDuanEvt(
        var id: UUID,
        var zhenDuan: String
)

data class WenZhen_AnPaiYiShengCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var wenZhenZhangHaoId: UUID,
        var bingLiBianJiZhangHaoId: UUID,
        var kaiJuChuFangZhangHaoId: UUID,
        var queRenChuFangZhangHaoId: UUID
)

data class WenZhen_AnPaiYiShengEvt(
        var id: UUID,
        var wenZhenZhangHaoId: UUID,
        var bingLiBianJiZhangHaoId: UUID,
        var kaiJuChuFangZhangHaoId: UUID,
        var queRenChuFangZhangHaoId: UUID
)

data class WenZhen_ZhuanZhenCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var wenZhenZhangHaoId: UUID,
        var bingLiBianJiZhangHaoId: UUID,
        var kaiJuChuFangZhangHaoId: UUID,
        var queRenChuFangZhangHaoId: UUID
)

data class WenZhen_ZhuanZhenEvt(
        var id: UUID,
        var wenZhenZhangHaoId: UUID,
        var bingLiBianJiZhangHaoId: UUID,
        var kaiJuChuFangZhangHaoId: UUID,
        var queRenChuFangZhangHaoId: UUID
)

data class WenZhen_AnPaiHuiZhenCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var shiJian: LocalDateTime,
        var lianJie: String,
        var huiYiId: String,
        var huanFangCanYuRenYuan: String,
        var beiZhu: String?
)

data class WenZhen_AnPaiHuiZhenEvt(
        var id: UUID,
        var shiJian: LocalDateTime,
        var lianJie: String,
        var huiYiId: String,
        var huanFangCanYuRenYuan: String,
        var beiZhu: String?
)

data class WenZhen_SheZhiHuiZhenShiPinCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var shiPinLianJie: String
)

data class WenZhen_SheZhiHuiZhenShiPinEvt(
        var id: UUID,
        var shiPinLianJie: String
)