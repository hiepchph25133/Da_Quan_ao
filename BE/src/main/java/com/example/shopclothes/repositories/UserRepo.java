package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.User;
import com.example.shopclothes.entity.propertis.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<User, Long> {


//    @Query("SELECT u FROM User u WHERE " +
//            "(:keyword IS NULL OR u.usersName LIKE %:keyword% OR CAST(u.id AS STRING) = :keyword OR " +
//            "u.phoneNumber LIKE %:keyword% OR u.email LIKE %:keyword%) AND " +
//            "(:birthday IS NULL OR u.birthday = :birthday OR :birthday IS NULL) AND " +
//            "(:sex IS NULL OR u.sex = :sex) AND " +
//            "(:status IS NULL OR u.status = :status)" +
//            "ORDER BY u.dateCreate DESC")
//    Page<User> getUsersByFilter(
//            @Param("keyword") String keyword,
//            @Param("birthday") Date birthday,
//            @Param("sex") Boolean sex,
//            @Param("status") Status status,
//            Pageable pageable
//    );

    Optional<User> findByEmailAndStatusTrue(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:keyword IS NULL OR u.usersName LIKE %:keyword% OR CAST(u.id AS STRING) = :keyword OR " +
            "u.phoneNumber LIKE %:keyword% OR u.email LIKE %:keyword%) AND " +
            "(:birthday IS NULL OR DATE_FORMAT(u.birthday, '%d/%m/%Y') = :birthday OR :birthday IS NULL) AND " +
            "(:sex IS NULL OR u.sex = :sex) AND " +
            "(:status IS NULL OR u.status = :status)" +
            "ORDER BY u.dateCreate DESC")
    Page<User> getUsersByFilter(
            @Param("keyword") String keyword,
            @Param("birthday") String birthday,
            @Param("sex") Boolean sex,
            @Param("status") Status status,
            Pageable pageable
    );




}
