package com.qtc.hospitalcore.domain.jiankangdangan;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM JianKangDangAnView r " +
                    "WHERE r.deleted = false " +
                    "AND r.xingMing LIKE %:keyword%"
    )
    public Page<JianKangDangAnView> findAllByKeyword(String keyword, Pageable pageable);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM JianKangDangAnView r " +
                    "WHERE r.deleted = false " +
                    "AND r.yongHuId = :yongHuId"
    )
    public Page<JianKangDangAnView> findAllByYongHuId(UUID yongHuId);
}
