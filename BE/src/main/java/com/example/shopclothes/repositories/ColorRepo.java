package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Color;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public interface ColorRepo extends JpaRepository<Color, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Color set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);


    @Query(value = "select c from Color c where c.status = 'DANG_HOAT_DONG'")
    List<Color> getAll();


    Page<Color> getAllByStatus(Status status, Pageable pageable);

    @Query("SELECT c FROM Color c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
    Color findByColorName(String colorName);


//    @Query("SELECT c FROM Color c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
//    Color findFirstByColorName(String colorName);


    @Query("SELECT c FROM Color c WHERE c.status = 'DANG_HOAT_DONG' AND c.colorName = :name ORDER BY c.dateCreate DESC")
    Optional<Color> findFirstByColorName(@Param("name") String name);
}
