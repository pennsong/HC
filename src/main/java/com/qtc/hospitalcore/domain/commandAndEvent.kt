package com.qtc.hospitalcore.domain

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

/**
 * YiHuRenYuan
 */
data class YiHuRenYuan_ChuangJianCmd(
        @TargetAggregateIdentifier
        val id: UUID,
        val quanXianSet: Set<YiHuRenYuan.QuanXian>,
        val xinXiMap: Map<String, Object>
)

data class YiHuRenYuan_ChuangJianEvt(
        val id: UUID,
        val quanXianSet: Set<YiHuRenYuan.QuanXian>,
        val xinXiMap: Map<String, Object>
)

