package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface VocherRepo extends JpaRepository<Vocher, Long> {

    @Modifying
    @Transactional
    @Query(value = " update Vocher set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);

    @Query("SELECT v FROM Vocher v")
    List<Vocher> getAll();

    @Query("SELECT v FROM Vocher v WHERE " +
            "(:keyword IS NULL OR v.name LIKE %:keyword% OR v.code LIKE %:keyword% OR CAST(v.discountRate AS STRING) LIKE %:keyword%) AND " +
            "(:startTime IS NULL OR v.startTime >= :startTime) AND " +
            "(:endTime IS NULL OR v.endTime <= :endTime) AND " +
            "(:status IS NULL OR v.status = :status) " +
            "ORDER BY v.dateCreate DESC")
    Page<Vocher> getVoucherByFilter(
            @Param("keyword") String keyword,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("status") Integer status,
            Pageable pageable
    );

    Vocher findByCode(String code);
}
