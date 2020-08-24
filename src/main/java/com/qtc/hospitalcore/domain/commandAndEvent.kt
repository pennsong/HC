package com.qtc.hospitalcore.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

/**
 * External
 */
data class ExtChuangJianYongHuCmd(
        @TargetAggregateIdentifier
        var zhangHaoId: UUID,
        var shouJiHao: String,
        var weiXinOpenId: String
)

data class ExtChuangJianYiHuRenYuanCmd(
        @TargetAggregateIdentifier
        var zhangHaoId: UUID,
        var username: String,
        var password: String,
        var xingMing: String,
        var shenFenZhengHao: String,
        var yiHuRenYuan_XinXiMap: Map<String, Any>
)

data class ExtJianChaChanPinCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

