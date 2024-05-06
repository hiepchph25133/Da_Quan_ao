package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.PaymentsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface PaymentDetailRepo extends JpaRepository<PaymentsDetail, Long> {

    @Modifying
    @Transactional
    @Query(value = "update PaymentsDetail set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);
}
