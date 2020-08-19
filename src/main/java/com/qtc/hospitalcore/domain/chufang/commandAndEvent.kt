package com.qtc.hospitalcore.domain.chufang

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime
import java.util.*

data class ChuFang_KaiJuCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var wenZhenId: UUID,
        var zhengWen: String,
        var kaiJuZhangHaoId: UUID
)

data class ChuFang_KaiJuEvt(
        var id: UUID,
        var wenZhenId: UUID,
        var zhengWen: String,
        var kaiJuZhangHaoId: UUID,
        var kaiJuShiJian: LocalDateTime
)

data class ChuFang_QueRenCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var queRenZhangHaoId: UUID
)

data class ChuFang_QueRenEvt(
        var id: UUID,
        var queRenZhangHaoId: UUID,
        var queRenShiJian: LocalDateTime
)

data class ChuFang_QuXiaoCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var quXiaoZhangHaoId: UUID
)

data class ChuFang_QuXiaoEvt(
        var id: UUID,
        var quXiaoZhangHaoId: UUID
)