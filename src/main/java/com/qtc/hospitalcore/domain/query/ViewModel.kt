package com.qtc.hospitalcore.domain.query

import com.qtc.hospitalcore.domain.YiHuRenYuan
import com.qtc.hospitalcore.domain.ZhangHao
import com.qtc.hospitalcore.domain.util.HashMapConverter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.*

@Entity
data class ZhangHaoView(
        @Id
        val id: UUID,
        val username: String,
        val password: String,
        @ElementCollection
        @Enumerated(EnumType.STRING)
        val jueSeSet: Set<ZhangHao.JueSe>
)

@Repository
interface ZhangHaoViewRepository : JpaRepository<ZhangHaoView, UUID>

@Entity
data class YongHuView(
        @Id
        val id: UUID,
        val openId: String,
        val shouJiHao: String,
        val yanZhengMa: String,
        val yanZhengMaDeadline: OffsetDateTime,
        @Convert(converter = HashMapConverter::class)
        val xinXiMap: Map<String, Object>
)

@Repository
interface YongHuViewRepository : JpaRepository<YongHuView, UUID>

@Entity
data class YiHuRenYuanView(
        @Id
        val id: UUID,
        @ElementCollection
        @Enumerated(EnumType.STRING)
        val quanXianSet: Set<YiHuRenYuan.QuanXian>,
        @Convert(converter = HashMapConverter::class)
        val xinXiMap: Map<String, Object>
)

@Repository
interface YiHuRenYuanViewRepository : JpaRepository<YiHuRenYuanView, UUID>

@Entity
data class WenZhenView(
        @Id
        val id: UUID,
        @Convert(converter = HashMapConverter::class)
        val xinXiMap: Map<String, Object>
)

@Repository
interface WenZhenViewRepository : JpaRepository<WenZhenView, UUID>

@Entity
data class ChangPinView(
        @Id
        val id: UUID,
        val fuWuDaFenLei: String,
        val fuWuLeiXing: String,
        val chanPingMing: String,
        val yuFuFei: BigDecimal,
        val shiChangJia: BigDecimal,
        @Convert(converter = HashMapConverter::class)
        val xinXiMap: Map<String, Object>
)

@Repository
interface ChangPinViewRepository : JpaRepository<ChangPinView, UUID>