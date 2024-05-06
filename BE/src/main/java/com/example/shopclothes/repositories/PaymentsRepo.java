package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface PaymentsRepo extends JpaRepository<Payments, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Payments set status = 0 where id = ?1", nativeQuery = true)
    void delete(Long id);

    List<Payments> findByOrdersId(Long orderId);
}
