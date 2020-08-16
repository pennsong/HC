package com.qtc.hospitalcore.domain.yonghu

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class YongHu_ChuangJianCmd(
        @TargetAggregateIdentifier
        var yongHuId: UUID,
        var shouJiHaoMa: String,
        var weiXinOpenId: String
)

data class YongHu_ChuangJianEvt(
        var yongHuId: UUID,
        var shouJiHaoMa: String,
        var weiXinOpenId: String
)

data class YongHu_ChuangJianJiBenXinXiCmd(
        @TargetAggregateIdentifier
        var yongHuId: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var jiBenXinXiMap: Map<String, Any?>
)

data class YongHu_ChuangJianJiBenXinXiEvt(
        var yongHuId: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var jiBenXinXiMap: Map<String, Any?>
)


