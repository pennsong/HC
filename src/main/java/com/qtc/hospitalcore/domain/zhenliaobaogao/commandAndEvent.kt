package com.qtc.hospitalcore.domain.zhenliaobaogao

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.OffsetDateTime
import java.util.*

data class ZhenLiaoBaoGao_KaiJuCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var wenZhenId: UUID,
        var zhengWen: String,
        var kaiJuZhangHaoId: UUID
)

data class ZhenLiaoBaoGao_KaiJuEvt(
        var id: UUID,
        var wenZhenId: UUID,
        var zhengWen: String,
        var kaiJuZhangHaoId: UUID,
        var kaiJuShiJian: OffsetDateTime
)