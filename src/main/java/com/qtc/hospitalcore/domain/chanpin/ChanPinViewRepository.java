package com.qtc.hospitalcore.domain.chanpin;

import com.qtc.hospitalcore.domain.wenzhen.WenZhenView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChanPinViewRepository extends JpaRepository<ChanPinView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM ChanPinView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<ChanPinView> findById(UUID id);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM ChanPinView r " +
                    "WHERE r.deleted = false " +
                    "AND r.mingCheng LIKE %:keyword%"
    )
    public Page<ChanPinView> findAllByKeyword(String keyword, Pageable pageable);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM ChanPinView r " +
                    "WHERE r.deleted = false " +
                    "AND r.zhuangTai = 'ZAI_SHOU' " +
                    "AND r.mingCheng LIKE %:keyword%"
    )
    public Page<ChanPinView> findAllKeGouByKeyword(String keyword, Pageable pageable);

    @Query(value = "" +
            "SELECT r " +
            "FROM ChanPinView r " +
            "WHERE r.deleted = false " +
            "AND r.mingCheng = ?1 ")
    public Optional<ChanPinView> findByMingCheng(String mingCheng);
}
