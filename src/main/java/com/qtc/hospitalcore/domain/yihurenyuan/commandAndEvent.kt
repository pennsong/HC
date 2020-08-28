package com.qtc.hospitalcore.domain.yihurenyuan

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

enum class QuanXian {
        WEN_ZHEN,
        KAI_JU_CHU_FANG,
        QUE_REN_CHU_FANG,
        BIAN_JI_BING_LI
}

data class YiHuRenYuan_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var quanXianMap: Map<QuanXian, Boolean>,
        var xinXiMap: Map<String, Any>
)

data class YiHuRenYuan_ChuangJianEvt(
        var id: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var quanXianMap: Map<QuanXian, Boolean>,
        var xinXiMap: Map<String, Any>
)

data class YiHuRenYuan_GengXinCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var xinXiMap: Map<String, Any>
)

data class YiHuRenYuan_GengXinEvt(
        var id: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var xinXiMap: Map<String, Any>
)

data class YiHuRenYuan_SheZhiQuanXianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var quanXianMap: Map<QuanXian, Boolean>
)

data class YiHuRenYuan_SheZhiQuanXianEvt(
        var id: UUID,
        var quanXianMap: Map<QuanXian, Boolean>
)
