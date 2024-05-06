package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Category;
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

public interface ProducerRepo extends JpaRepository<Producer, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Producer set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Query(value = "select c from Producer c where c.status = 'DANG_HOAT_DONG'")
    List<Producer> getAll();

    Page<Producer> getAllByStatus(Status status, Pageable pageable);

// Seacrh
@Query(value = "SELECT * FROM producer " +
        "WHERE (:key IS NULL OR producer.code LIKE CONCAT('%', :key, '%')) " +
        "AND (:key IS NULL OR producer.name LIKE CONCAT('%', :key , '%')) " +
        "AND (:status IS NULL OR producer.status = :status)",
        nativeQuery = true)
Page<Producer> searchPageNSX(@Param("key") String key,
                             @Param("status") Integer trangThai,
                             Pageable pageable);



    @Query("SELECT c FROM Producer c WHERE c.status = 'DANG_HOAT_DONG' AND c.producerName = :name ORDER BY c.dateCreate DESC")
    Optional<Producer> findByProducerName(@Param("name") String name);

//    @Query("SELECT s FROM Producer s WHERE s.status = 'DANG_HOAT_DONG' ORDER BY s.dateCreate DESC")
//    Producer findByProducerName(String name);

    @Query("SELECT s FROM Producer s WHERE s.status = 'DANG_HOAT_DONG' ORDER BY s.dateCreate DESC")
    List<Producer> findByDeletedTrue();

}
