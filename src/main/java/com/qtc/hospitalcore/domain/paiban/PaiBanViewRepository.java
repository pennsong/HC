package com.qtc.hospitalcore.domain.paiban;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaiBanViewRepository extends JpaRepository<PaiBanView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM PaiBanView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<PaiBanView> findById(UUID id);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM PaiBanView r " +
                    "WHERE r.deleted = false " +
                    "AND r.yiSheng LIKE %:keyword%"
    )
    public Page<PaiBanView> findAllByKeyword(String keyword, Pageable pageable);
}
