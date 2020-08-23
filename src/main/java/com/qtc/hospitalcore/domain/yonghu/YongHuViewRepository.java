package com.qtc.hospitalcore.domain.yonghu;

import com.qtc.hospitalcore.domain.yihurenyuan.YiHuRenYuanView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface YongHuViewRepository extends JpaRepository<YongHuView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM YongHuView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<YongHuView> findById(UUID id);
}
