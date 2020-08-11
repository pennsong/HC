package com.qtc.hospitalcore.domain

import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuan
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

/**
 * External
 */


/**
 * ZhangHao
 */
data class XinJianZhangHaoCmd(
        @TargetAggregateIdentifier
        val id: String
)

data class XinJianZhangHaoEvt(
        @TargetAggregateIdentifier
        val id: String
)

data class XinJianZhangHaoCmd2(
        @TargetAggregateIdentifier
        val id: String
)

data class XinJianZhangHaoEvt2(
        @TargetAggregateIdentifier
        val id: String
)

data class XinJianZhangHaoCmd3(
        @TargetAggregateIdentifier
        val id: String
)


