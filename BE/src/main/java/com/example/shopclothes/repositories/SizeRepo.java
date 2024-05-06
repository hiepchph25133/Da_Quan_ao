package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Size;
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

public interface SizeRepo extends JpaRepository<Size, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Size set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "select c from Size c where c.status = 'DANG_HOAT_DONG'")
    List<Size> getAll();

    Page<Size> getAllByStatus(Status status, Pageable pageable);


//    Size findBySizeName(String sizeName);


//    @Query("SELECT c FROM Size c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
//    Size findFirstBySizeName(String sizeName);


    @Query("SELECT c FROM Size c WHERE c.status = 'DANG_HOAT_DONG' AND c.sizeName = :name ORDER BY c.dateCreate DESC")
    Optional<Size> findFirstBySizeName(@Param("name") String name);
}
