package com.qtc.hospitalcore.domain.jiankangdangan

import com.qtc.hospitalcore.domain.util.HashMapConverter
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*
import javax.persistence.Convert

enum class ZhuangTai {
    YI_CHUANG_JIAN,
    JIAN_KANG_XIN_XI_YI_GENG_XIN,
}

data class JianKangDangAn_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var yongHuId: UUID,
        var xingMing: String,
        var shenFenZheng: String,
        var shouJiHao: String,
        var jiBenXinXiMap: Map<String, Any>
)

data class JianKangDangAn_ChuangJianEvt(
        var id: UUID,
        var yongHuId: UUID,
        var xingMing: String,
        var shenFenZheng: String,
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

