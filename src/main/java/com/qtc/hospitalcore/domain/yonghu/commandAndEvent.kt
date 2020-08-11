package com.qtc.hospitalcore.domain.yonghu

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class ChuangJianYongHuCmd(
        @TargetAggregateIdentifier
        var yongHuId: UUID,
        var shouJiHaoMa: String
)

data class ChuangJianYongHuEvt(
        var yongHuId: UUID,
        var shouJiHaoMa: String
)

data class DiJiaoJiBenXinXiCmd(
        @TargetAggregateIdentifier
        var yongHuId: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var jiBenXinXiNeiRong: Map<String, Any>
)

data class DiJiaoJiBenXinXiEvt(
        var yongHuId: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var jiBenXinXiNeiRong: Map<String, Any>
)


