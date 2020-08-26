package com.qtc.hospitalcore.domain.zhanghao;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import javafx.print.Collation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ZhangHaoViewRepository extends JpaRepository<ZhangHaoView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM ZhangHaoView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<ZhangHaoView> findById(UUID id);
}
