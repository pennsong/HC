package com.qtc.hospitalcore.domain.wenzhenbaogao

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime
import java.util.*

data class WenZhenBaoGao_KaiJuCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var wenZhenId: UUID,
        var zhengWen: String,
        var kaiJuZhangHaoId: UUID
)

data class WenZhenBaoGao_KaiJuEvt(
        var id: UUID,
        var wenZhenId: UUID,
        var zhengWen: String,
        var kaiJuZhangHaoId: UUID,
        var kaiJuShiJian: LocalDateTime
)