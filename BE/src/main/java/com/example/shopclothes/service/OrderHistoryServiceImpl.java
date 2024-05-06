package com.example.shopclothes.service;

import com.example.shopclothes.dto.OrderHistoryRequestDto;
import com.example.shopclothes.entity.Order;
import com.example.shopclothes.entity.OrderHistory;

import java.util.List;

public interface OrderHistoryServiceImpl {

    public List<OrderHistory> findAllTimeLinesByOrderId(Long orderId);

    public OrderHistory findByOrderAndStatusId(Order order, Long status);

    public List<OrderHistory> findByOrderIdAndStatus(Long id);

    public Boolean createTimeLine(OrderHistoryRequestDto orderHistoryRequestDto);
}
