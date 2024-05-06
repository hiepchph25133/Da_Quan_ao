package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Imege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Repository

public interface ImageRepo extends JpaRepository<Imege, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Imege set status = 0 where id =?1", nativeQuery = true)
    void delete(Long id);

    @Query("SELECT i FROM Imege i WHERE i.product.id = :productId")
    List<Imege> findImegeByIdCtsp(@Param("productId") Long productId);

    List<Imege> findByImageLink(String imageLink);
}
