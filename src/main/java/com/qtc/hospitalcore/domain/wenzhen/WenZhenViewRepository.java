package com.qtc.hospitalcore.domain.wenzhen;

import com.qtc.hospitalcore.domain.paiban.PaiBanView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WenZhenViewRepository extends JpaRepository<WenZhenView, UUID> {
    @Query(value = "" +
            "SELECT r " +
            "FROM WenZhenView r " +
            "WHERE r.deleted = false " +
            "AND r.id = ?1 ")
    public Optional<WenZhenView> findById(UUID id);
}
