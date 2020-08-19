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

data class WenZhen_JianKangDangAnGengXinCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var jianKangDangAnMap: Map<String, Any>
)

data class WenZhen_JianKangDangAnGengXinEvt(
        var id: UUID,
        var jianKangDangAnMap: Map<String, Any>
)