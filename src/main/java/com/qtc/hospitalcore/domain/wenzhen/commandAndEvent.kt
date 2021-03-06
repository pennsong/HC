package com.qtc.hospitalcore.domain.wenzhen

import com.qtc.hospitalcore.domain.util.BiZhong
import com.qtc.hospitalcore.domain.wenzhen.WenZhen.HuiZhen
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

enum class HuiZhenZhuangTai {
    YI_AN_PAI,
    YI_WAN_CHENG,
}

enum class ChuFangZhuangTai {
    YI_KAI_JU,
    YI_QUE_REN,
    YI_QU_XIAO,
}

enum class JieGuo {
    CHENG_GONG,
    JIE_SHU,
}

enum class ZhuangTai {
    YI_CHUANG_JIAN,
    YI_AN_PAI_YI_SHENG,
    YI_CHENG_GONG_WAN_CHENG,
    YI_JIE_SHU_WAN_CHENG,
}

enum class FuFeiZhuangTai {
    WEI_FU_FEI,
    YI_ZHI_FU_YU_FU_FEI,
    YI_ZHI_FU_QUAN_KUAN,
}

data class WenZhen_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var jianKangDangAnId: UUID,
        var xinXiMap: Map<String, Any>,
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
        var xinXiMap: Map<String, Any>,
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
        var biZhong: BiZhong,
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
        var biZhong: BiZhong,
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

data class WenZhen_JieShuWanChengCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var beiZhu: String?
)

data class WenZhen_JieShuWanChengEvt(
        var id: UUID,
        var beiZhu: String?
)

data class WenZhen_KaiJuWenZhenBaoGaoCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhengWen: String,
        var zhangHaoId: UUID
)

data class WenZhen_KaiJuWenZhenBaoGaoEvt(
        var id: UUID,
        var wenZhenBaoGaoId: UUID,
        var zhengWen: String,
        var zhangHaoId: UUID,
        var shiJian: OffsetDateTime
)

data class WenZhen_KaiJuZhenLiaoBaoGaoCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhengWen: String,
        var zhangHaoId: UUID,
        var shiJian: OffsetDateTime
)

data class WenZhen_KaiJuZhenLiaoBaoGaoEvt(
        var id: UUID,
        var zhenLiaoBaoGaoId: UUID,
        var zhengWen: String,
        var zhangHaoId: UUID,
        var shiJian: OffsetDateTime
)

data class WenZhen_KaiJuChuFangCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhengWen: String,
        var zhangHaoId: UUID
)

data class WenZhen_KaiJuChuFangEvt(
        var id: UUID,
        var zhengWen: String,
        var zhangHaoId: UUID,
        var shiJian: OffsetDateTime
)

data class WenZhen_QueRenChuFangCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhangHaoId: UUID
)

data class WenZhen_QueRenChuFangEvt(
        var id: UUID,
        var zhangHaoId: UUID,
        var shiJian: OffsetDateTime
)

data class WenZhen_QuXiaoChuFangCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var zhangHaoId: UUID
)

data class WenZhen_QuXiaoChuFangEvt(
        var id: UUID,
        var zhangHaoId: UUID
)