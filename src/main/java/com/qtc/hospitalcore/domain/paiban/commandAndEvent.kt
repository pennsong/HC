package com.qtc.hospitalcore.domain.paiban

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class PaiBan_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var chanPinId: UUID,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var yiSheng: String,
        var shiJian: OffsetDateTime,
        var xinXiMap: Map<String, Any>
)

data class PaiBan_ChuangJianEvt(
        var id: UUID,
        var chanPinId: UUID,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var yiSheng: String,
        var shiJian: OffsetDateTime,
        var xinXiMap: Map<String, Any>
)

data class PaiBan_ShangJiaCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class PaiBan_ShangJiaEvt(
        var id: UUID
)

data class PaiBan_XiaJiaCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class PaiBan_XiaJiaEvt(
        var id: UUID
)

data class PaiBan_ShouChuCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class PaiBan_ShouChuEvt(
        var id: UUID
)

data class PaiBan_ShanChuCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class PaiBan_ShanChuEvt(
        var id: UUID
)