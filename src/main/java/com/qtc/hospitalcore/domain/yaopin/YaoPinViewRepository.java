package com.qtc.hospitalcore.domain.yaopin;

import com.qtc.hospitalcore.domain.chanpin.ChanPinView;
import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface YaoPinViewRepository extends JpaRepository<YaoPinView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM YaoPinView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<YaoPinView> findById(UUID id);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM YaoPinView r " +
                    "WHERE r.deleted = false " +
                    "AND r.mingCheng LIKE %:keyword%"
    )
    public Page<YaoPinView> findAllByKeyword(String keyword, Pageable pageable);
}
