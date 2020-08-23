package com.qtc.hospitalcore.domain.wenzhen

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.time.OffsetDateTime
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

        var xiaDanShiJian: OffsetDateTime
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

        var shiJian: OffsetDateTime
)

data class WenZhen_ZhiXingTuiKuanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: OffsetDateTime,
        var shouKuanZhangHuMing: String,
        var shouKuanZhangHu: String,
        var jinE: BigDecimal,
        var beiZhu: String?,
        var pingZhengList: List<String>
)

data class WenZhen_ZhiXingTuiKuanEvt(
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: OffsetDateTime,
        var shouKuanZhangHuMing: String,
        var shouKuanZhangHu: String,
        var jinE: BigDecimal,
        var beiZhu: String?,
        var pingZhengList: List<String>
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
        var shiJian: OffsetDateTime,
        var fuKuanFang: String,
        var biZhong: String,
        var jinE: BigDecimal,
        var fuKuanDangRiHuiLv: Double,
        var beiZhu: String?,
        var pingZhengList: List<String>
)

data class WenZhen_ZhiFuBuChongKuanEvt(
        var id: UUID,
        var liuShuiHao: String,
        var shiJian: OffsetDateTime,
        var fuKuanFang: String,
        var biZhong: String,
        var jinE: BigDecimal,
        var fuKuanDangRiHuiLv: Double,
        var beiZhu: String?,
        var pingZhengList: List<String>
)

// 由WenZhen_ZhiFuBuChongKuanCmd触发
data class WenZhen_ZhiFuQuanKuanEvt(
        var id: UUID
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
        var shiJian: OffsetDateTime,
        var lianJie: String,
        var huiYiId: String,
        var huanFangCanYuRenYuan: String,
        var beiZhu: String?
)

data class WenZhen_AnPaiHuiZhenEvt(
        var id: UUID,
        var shiJian: OffsetDateTime,
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

data class WenZhen_GengXinMuQianZhuYaoZhenDuanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhenDuan: String
)

data class WenZhen_GengXinMuQianZhuYaoZhenDuanEvt(
        var id: UUID,
        var zhenDuan: String
)


data class WenZhen_GengXinXiangXiZhiLiaoJingGuoCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var xiangXiZhiLiaoJingGuoMap: Map<String, Any>
)

data class WenZhen_GengXinXiangXiZhiLiaoJingGuoEvt(
        var id: UUID,
        var xiangXiZhiLiaoJingGuoMap: Map<String, Any>
)

data class WenZhen_GengXinJianChaZongJieCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var jianChaZongJieMap: Map<String, Any>
)

data class WenZhen_GengXinJianChaZongJieEvt(
        var id: UUID,
        var jianChaZongJieMap: Map<String, Any>
)

data class WenZhen_GengXinDianZiYingXiangCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var dianZiYingXiangMap: Map<String, Any>
)

data class WenZhen_GengXinDianZiYingXiangEvt(
        var id: UUID,
        var dianZiYingXiangMap: Map<String, Any>
)

data class WenZhen_GengXinQiTaCaiLiaoCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var qiTaCaiLiaoMap: Map<String, Any>
)

data class WenZhen_GengXinQiTaCaiLiaoEvt(
        var id: UUID,
        var qiTaCaiLiaoMap: Map<String, Any>
)

data class WenZhen_GengXinWenZhenZongJieCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var wenZhenZongJieMap: Map<String, Any>
)

data class WenZhen_GengXinWenZhenZongJieEvt(
        var id: UUID,
        var wenZhenZongJieMap: Map<String, Any>
)

data class WenZhen_ChengGongWanChengCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var beiZhu: String?
)

data class WenZhen_ChengGongWanChengEvt(
        var id: UUID,
        var beiZhu: String?
)

data class WenZhen_ZhongDuanWanChengCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var beiZhu: String?
)

data class WenZhen_ZhongDuanWanChengEvt(
        var id: UUID,
        var beiZhu: String?
)