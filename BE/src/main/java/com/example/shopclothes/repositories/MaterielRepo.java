package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.Product;
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

public interface MaterielRepo extends JpaRepository<Material, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Material set status = 'NGUNG_HOAT_DONG' where id = ?1", nativeQuery = true)
    void delete(Long id);


    @Query(value = "select c from Material c where c.status = 'DANG_HOAT_DONG'")
    List<Material> getAll();


    Page<Material> getAllByStatus(Status status, Pageable pageable);

//    @Query("SELECT c FROM Material c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
//    Material findByMaterialName(String name);


//    @Query("SELECT m FROM Material m WHERE m.status = 'DANG_HOAT_DONG' ORDER BY m.dateCreate DESC")
//    Material findFirstByMaterialName(String materialName);

    @Query("SELECT c FROM Material c WHERE c.status = 'DANG_HOAT_DONG' AND c.materialName = :name ORDER BY c.dateCreate DESC")
    Optional<Material> findFirstByMaterialName(@Param("name") String name);
}
