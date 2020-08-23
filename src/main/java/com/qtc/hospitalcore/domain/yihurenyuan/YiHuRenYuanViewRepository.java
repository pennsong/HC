package com.qtc.hospitalcore.domain.yihurenyuan;

import com.qtc.hospitalcore.domain.yaopin.YaoPinView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface YiHuRenYuanViewRepository extends JpaRepository<YiHuRenYuanView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM YiHuRenYuanView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<YiHuRenYuanView> findById(UUID id);
}
