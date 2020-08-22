package com.qtc.hospitalcore.domain.chanpin;

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
}
