package com.qtc.hospitalcore.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

/**
 * External
 */
data class ExtChuangJianYongHuCmd(
        @TargetAggregateIdentifier
        var zhangHaoId: UUID,
        var yongHuId: UUID,
        var shouJiHao: String,
        var weiXinOpenId: String
)

data class ExtChuangJianYiHuRenYuanCmd(
        @TargetAggregateIdentifier
        var zhangHaoId: UUID,
        var yiHuRenYuanId: UUID,
        var username: String,
        var password: String,
        var xingMing: String,
        var shenFenZheng: String,
        var yiHuRenYuan_XinXiMap: Map<String, Any>
)

data class ExtJianChaChanPinCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class ExtChuangJianPaiBanCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var chanPinId: UUID,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var yiSheng: String,
        var shiJian: OffsetDateTime,
        var xinXiMap: Map<String, Any>
)
