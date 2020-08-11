package com.qtc.hospitalcore.domain.query

import com.qtc.hospitalcore.domain.util.HashMapConverter
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView
import com.qtc.hospitalcore.domain.yonghu.YongHuView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*
import javax.persistence.*


@Repository
interface ZhangHaoViewRepository : JpaRepository<ZhangHaoView, UUID>

@Repository
interface YongHuViewRepository : JpaRepository<YongHuView, UUID>

@Repository
interface YiHuRenYuanViewRepository : JpaRepository<YiHuRenYuanView, UUID>

@Repository
interface WenZhenViewRepository : JpaRepository<WenZhenView, UUID>

@Entity
data class ChangPinView(
        @Id
        var id: UUID,
        var fuWuDaFenLei: String,
        var fuWuLeiXing: String,
        var chanPingMing: String,
        var yuFuFei: BigDecimal,
        var shiChangJia: BigDecimal,
        @Convert(converter = HashMapConverter::class)
        var xinXiMap: Map<String, Object>
)

@Repository
interface ChangPinViewRepository : JpaRepository<ChangPinView, UUID>