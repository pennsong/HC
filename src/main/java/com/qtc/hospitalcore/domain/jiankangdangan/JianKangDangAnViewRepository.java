package com.qtc.hospitalcore.domain.jiankangdangan;

import com.qtc.hospitalcore.domain.chufang.ChuFangView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JianKangDangAnViewRepository extends JpaRepository<JianKangDangAnView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM JianKangDangAnView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<JianKangDangAnView> findById(UUID id);
}
