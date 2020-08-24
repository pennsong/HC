package com.qtc.hospitalcore.domain.zhanghao

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*
import javax.annotation.Nullable

enum class JueSe {
        YONG_HU,
        YI_HU_REN_YUAN
}

data class ZhangHao_ChuangJianCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var username: String?,
        var password: String?,
        var jueSe: JueSe,
        var yongHuId: UUID?,
        var yiHuRenYuanId: UUID?
)

data class ZhangHao_ChuangJianEvt(
        var id: UUID,
        var username: String?,
        var password: String?,
        var jueSe: JueSe,
        var yongHuId: UUID?,
        var yiHuRenYuanId: UUID?
)

data class ZhangHao_SheZhiMiMaCmd(
        @TargetAggregateIdentifier
        var id: UUID,
        var password: String
)

data class ZhangHao_SheZhiMiMaEvt(
        var id: UUID,
        var password: String
)

data class ZhangHao_ShanChuCmd(
        @TargetAggregateIdentifier
        var id: UUID
)

data class ZhangHao_ShanChuEvt(
        var id: UUID
)


