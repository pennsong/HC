package com.qtc.hospitalcore.domain.paiban;

import com.qtc.hospitalcore.domain.jiankangdangan.JianKangDangAnView;
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
}
