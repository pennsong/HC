package com.qtc.hospitalcore.domain.chongFuJianCe

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface YongHuShouJiHaoRepository : JpaRepository<YongHuShouJiHao, Long>

@Repository
interface YongHuOpenIdRepository : JpaRepository<YongHuOpenId, Long>

@Repository
interface UsernameRepository : JpaRepository<Username, Long>

@Repository
interface YiHuRenYuanShenFenZhengRepository : JpaRepository<YiHuRenYuanShenFenZheng, Long>

@Repository
interface JianKangDangAnShenFenZhengRepository : JpaRepository<JianKangDangAnShenFenZheng, Long>
