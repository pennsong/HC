package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.paiban.PaiBanView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WenZhenViewRepository extends JpaRepository<WenZhenView, UUID> {
    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM WenZhenView r " +
                    "WHERE r.deleted = false " +
                    "AND r.id = ?1 ")
    public Optional<WenZhenView> findById(UUID id);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM WenZhenView r " +
                    "WHERE r.deleted = false "
    )
    public Page<WenZhenView> findAllByKeyword(String keyword, Pageable pageable);

    @Query(
            value = "" +
                    "SELECT r " +
                    "FROM WenZhenView r " +
                    "WHERE r.deleted = false " +
                    "AND r.jianKangDangAnId = :jianKangDangAnId"
    )
    public List<WenZhenView> findAllByJianKangDangAnId(UUID jianKangDangAnId);
}
