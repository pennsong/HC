package com.qtc.hospitalcore.domain.yihurenyuan

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class YiHuRenYuan_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var dengluMing: String,
        var dengLuMiMa: String,
        var xingMing: String,
        var quanXianSet: Set<YiHuRenYuan.QuanXian>,
        var xinXiMap: Map<String, Object>
)

data class YiHuRenYuan_ChuangJianEvt(
        var id: UUID,
        var dengluMing: String,
        var dengLuMiMa: String,
        var xingMing: String,
        var quanXianSet: Set<YiHuRenYuan.QuanXian>,
        var xinXiMap: Map<String, Object>
)
