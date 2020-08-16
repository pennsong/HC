package com.qtc.hospitalcore.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

/**
 * External
 */
data class ExtChuangJianYongHuCmd(
        @TargetAggregateIdentifier
        var dummyId: UUID,
        var shouJiHao: String,
        var weiXinOpenId: String
)

data class ExtChuangJianYiHuRenYuanCmd(
        @TargetAggregateIdentifier
        var dummyId: UUID,
        var username: String,
        var password: String,
        var xingMing: String,
        var shenFenZhengHao: String,
        var yiHuRenYuan_XinXiMap: Map<String, Any>
)

