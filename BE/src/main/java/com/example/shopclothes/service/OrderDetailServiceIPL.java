package com.example.shopclothes.service;

import com.example.shopclothes.dto.OrderDetailInStoreRequestDto;
import com.example.shopclothes.dto.OrderDetailResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDetailServiceIPL {
    public Boolean createOrderDetail(OrderDetailInStoreRequestDto orderDetailInStoreRequestDto);

    public Page<OrderDetailResponseDto> getOrderDetailByOrderId(Long orderId, Pageable pageable);

    public Boolean deleteOrderDetail(Long id);
    public Boolean updateQuantityOrderDetail(Integer quantity, Long id);
}
