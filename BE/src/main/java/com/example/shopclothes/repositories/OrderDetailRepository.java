package com.example.shopclothes.repositories;

import com.example.shopclothes.dto.OrderDetailResponseDto;
import com.example.shopclothes.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(Long orderId);

    Optional<OrderDetail> findByOrderIdAndProductDetailId(Long orderId, Long productDetailId);


    @Query("SELECT new com.example.shopclothes.dto.OrderDetailResponseDto(" +
            "od.id,i.imageLink, c.colorName, s.sizeName, p.productName, od.quantity, od.price) " +
            "FROM OrderDetail od " +
            "JOIN od.productDetail pd " +
            "JOIN pd.idProduct p " +
            "JOIN pd.idColor c " +
            "JOIN pd.idSize s " +
            "JOIN Imege i ON i.product.id = p.id AND i.status = 'DANG_HOAT_DONG' " +
            "WHERE od.order.id = :orderId " +
            "ORDER BY od.createdAt DESC ")
    Page<OrderDetailResponseDto> findOrderDetailByOrderId(@Param("orderId") Long orderId, Pageable pageable);
}
