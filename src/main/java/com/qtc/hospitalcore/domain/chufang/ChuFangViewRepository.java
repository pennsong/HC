package com.qtc.hospitalcore.domain.chufang;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.zhanghao.ZhangHaoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChuFangViewRepository extends JpaRepository<ChuFangView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM ChanPinView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<ChuFangView> findById(UUID id);
}
