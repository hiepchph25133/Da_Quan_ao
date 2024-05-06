package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.orderStatus.statusName = 'Tạo đơn hàng' ")
    List<Order> findAllOrderByStatusName();

//    @Query("SELECT o FROM Order o WHERE " +
//            " (:orderStatusName IS NULL OR o.orderStatus.statusName = :orderStatusName) " +
//            "AND (:orderId IS NULL OR CAST(o.id AS STRING) = :orderId) " +
//            "AND (:orderType IS NULL OR o.orderType = :orderType) " +
//            "AND (:startDate IS NULL OR o.createdAt >= :startDate) " +
//            "AND (:endDate IS NULL OR o.createdAt <= :endDate) " +
//            "AND o.orderStatus.statusName <> 'Tạo đơn hàng' " +
//            "ORDER BY o.createdAt DESC")
//    Page<Order> findAllByStatusNameAndDeletedIsTrue(
//            @Param("orderStatusName") String orderStatusName,
//            @Param("orderId") String orderId,
//            @Param("orderType") String orderType,
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate,
//            Pageable pageable);

    @Query("SELECT o FROM Order o WHERE " +
            "(:orderStatusName IS NULL OR o.orderStatus.statusName = :orderStatusName) " +
            "AND (:orderId IS NULL OR o.id = :orderId) " +
            "AND (:orderType IS NULL OR o.orderType = :orderType) " +
            "AND (:startDate IS NULL OR o.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR o.createdAt <= :endDate) " +
            "AND o.orderStatus.statusName <> 'Tạo đơn hàng' " +
            "ORDER BY o.createdAt DESC")
    Page<Order> findAllByStatusNameAndDeletedIsTrue(
            @Param("orderStatusName") String orderStatusName,
            @Param("orderId") Long orderId,
            @Param("orderType") String orderType,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            Pageable pageable);

//    @Query("SELECT o FROM Order o WHERE " +
//            "(:orderStatusName IS NULL OR o.orderStatus.statusName = :orderStatusName) " +
//            "AND (:orderId IS NULL OR CAST(o.id AS STRING) = :orderId) " +
//            "AND (:orderType IS NULL OR o.orderType = :orderType) " +
//            "AND (:startDate IS NULL OR o.createdAt >= :startDate) " +
//            "AND (:endDate IS NULL OR o.createdAt <= :endDate) " +
//            "AND o.orderStatus.statusName <> 'Tạo đơn hàng' " +
//            "ORDER BY o.createdAt DESC")
//    Page<Order> findAllByStatusNameAndDeletedIsTrue(
//            @Param("orderStatusName") String orderStatusName,
//            @Param("orderId") String orderId,
//            @Param("orderType") String orderType,
//            @Param("startDate") LocalDateTime startDate,
//            @Param("endDate") LocalDateTime endDate,
//            Pageable pageable);



}
