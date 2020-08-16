package com.qtc.hospitalcore.domain.chanpin

import com.qtc.hospitalcore.domain.util.HashMapConverter
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*
import javax.persistence.Convert

data class ChanPin_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var chanPinMing: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var xinXiMap: Map<String, Any>
)

data class ChanPin_ChuangJianEvt(
        var id: UUID,
        var chanPinMing: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var xinXiMap: Map<String, Any>
)

data class ChanPin_GengXinCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var chanPinMing: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var xinXiMap: Map<String, Any>
)

data class ChanPin_GengXinEvt(
        var id: UUID,
        var chanPinMing: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        var xinXiMap: Map<String, Any>
)

data class ChanPin_ShangJiaCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class ChanPin_ShangJiaEvt(
        var id: UUID
)

data class ChanPin_XiaJiaCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class ChanPin_XiaJiaEvt(
        var id: UUID
)

data class ChanPin_ShanChuCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class ChanPin_ShanChuEvt(
        var id: UUID
)