package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AddressRepo extends JpaRepository<Address, Long> {

//    @Query("SELECT a FROM Address a WHERE a.idUser.id = :userId and a.status = 'DANG_HOAT_DONG'")
//    Address findAddressesByUserIdAndStatus(@Param("userId") Long userId);

    @Query("SELECT a FROM Address a WHERE a.idUser.id = :userId AND a.status = true ")
    Address findAddressesByUserIdAndStatusTrue(@Param("userId") Long userId);

    @Query("SELECT a FROM Address a WHERE a.idUser.id = :userId")
    List<Address> findByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Address a SET a.status = false WHERE (a.status = true) AND a.idUser.id = :userId")
    @Transactional
    void updateDefaultAddress(@Param("userId") Long userId);

}
