package com.qtc.hospitalcore.domain.yaopin

import com.qtc.hospitalcore.domain.util.HashMapConverter
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*
import javax.persistence.Convert

data class YaoPin_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var mingCheng: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var xinXiMap: Map<String, Any>
)

data class YaoPin_ChuangJianEvt(
        var id: UUID,
        var mingCheng: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var xinXiMap: Map<String, Any>
)

data class YaoPin_GengXinCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var mingCheng: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var xinXiMap: Map<String, Any>
)

data class YaoPin_GengXinEvt(
        var id: UUID,
        var mingCheng: String,
        var daLeiXing: String,
        var xiaoLeiXing: String,
        var xinXiMap: Map<String, Any>
)

data class YaoPin_ShangJiaCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class YaoPin_ShangJiaEvt(
        var id: UUID
)

data class YaoPin_XiaJiaCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class YaoPin_XiaJiaEvt(
        var id: UUID
)

data class YaoPin_ShanChuCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class YaoPin_ShanChuEvt(
        var id: UUID
)