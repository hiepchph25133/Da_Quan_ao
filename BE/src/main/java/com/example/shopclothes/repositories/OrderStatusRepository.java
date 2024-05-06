package com.example.shopclothes.repositories;

import com.example.shopclothes.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {

    Boolean existsByStatusName(String statusName);

    Optional<OrderStatus> findByStatusName(String statusName);
}

