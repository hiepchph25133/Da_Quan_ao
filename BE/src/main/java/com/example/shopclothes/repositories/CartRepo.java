package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface CartRepo extends JpaRepository<Cart, Long> {

    @Modifying
    @Transactional
//    @Query(value = "update Cart set status = 0 where id =?1", nativeQuery = true)
    @Query(value = "update Cart set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);
}
