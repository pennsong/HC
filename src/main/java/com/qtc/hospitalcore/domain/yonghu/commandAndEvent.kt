package com.qtc.hospitalcore.domain.yonghu

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class YongHu_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var shouJiHao: String,
        var weiXinOpenId: String
)

data class YongHu_ChuangJianEvt(
        var id: UUID,
        var shouJiHao: String,
        var weiXinOpenId: String
)

data class YongHu_ChuangJianJiBenXinXiCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var xinXiMap: Map<String, Any?>
)

data class YongHu_ChuangJianJiBenXinXiEvt(
        var id: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var xinXiMap: Map<String, Any?>
)


