package com.qtc.hospitalcore.domain.query

import com.qtc.hospitalcore.domain.chanpin.ChanPinView
import com.qtc.hospitalcore.domain.paiban.PaiBanView
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView
import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView
import com.qtc.hospitalcore.domain.yonghu.YongHuView
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface YongHuViewRepository : JpaRepository<YongHuView, UUID>

@Repository
interface YiHuRenYuanViewRepository : JpaRepository<YiHuRenYuanView, UUID>