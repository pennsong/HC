package com.qtc.hospitalcore.domain.jiankangdangan

import com.qtc.hospitalcore.domain.util.HashMapConverter
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*
import javax.persistence.Convert

data class JianKangDangAn_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var xingMing: String,
        var shenFenZhengHao: String,
        var shouJiHao: String,
        var jiBenXinXiMap: Map<String, Any>
)

data class JianKangDangAn_ChuangJianEvt(
        var id: UUID,
        var xingMing: String,
        var shenFenZhengHao: String,
        var shouJiHao: String,
        var jiBenXinXiMap: Map<String, Any>
)

data class JianKangDangAn_GengXinJianKangXinXiCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var jianKangXinXiMap: Map<String, Any>
)

data class JianKangDangAn_GengXinJianKangXinXiEvt(
        var id: UUID,
        var jianKangXinXiMap: Map<String, Any>
)
