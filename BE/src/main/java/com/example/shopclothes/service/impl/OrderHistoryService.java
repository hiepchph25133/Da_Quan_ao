package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.OrderHistoryRequestDto;
import com.example.shopclothes.entity.Order;
import com.example.shopclothes.entity.OrderHistory;
import com.example.shopclothes.entity.OrderStatus;
import com.example.shopclothes.repositories.OrderHistoryRepository;
import com.example.shopclothes.repositories.OrderRepository;
import com.example.shopclothes.repositories.OrderStatusRepository;
import com.example.shopclothes.service.OrderHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryService implements OrderHistoryServiceImpl {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderHistory> findAllTimeLinesByOrderId(Long orderId) {
        return orderHistoryRepository.findAllByStatusId(orderId);
    }

    @Override
    public OrderHistory findByOrderAndStatusId(Order order, Long status) {
        return orderHistoryRepository.findByOrderAndStatusId(order,status);
    }

    @Override
    public List<OrderHistory> findByOrderIdAndStatus(Long id) {
        return orderHistoryRepository.findByOrderIdAndStatus(id);
    }

    @Override
    public Boolean createTimeLine(OrderHistoryRequestDto orderHistoryRequestDto) {
        Long orderId = orderHistoryRequestDto.getOrderId();
        Long orderStatusId = orderHistoryRequestDto.getStatusId();
        Order order = orderId != null ? orderRepository.findById(orderId).orElse(null) : null;
        OrderStatus orderStatus = orderId != null ? orderStatusRepository.findById(orderStatusId).orElse(null) : null;

        OrderHistory timeLine = new OrderHistory();
        timeLine.setNote(orderHistoryRequestDto.getNote());
        timeLine.setStatus(orderStatus);
        timeLine.setOrder(order);
        orderHistoryRepository.save(timeLine);
        return true;
    }
}
