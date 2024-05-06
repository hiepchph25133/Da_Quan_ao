package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.PaymentRequestDto;
import com.example.shopclothes.entity.Order;
import com.example.shopclothes.entity.Payments;
import com.example.shopclothes.repositories.OrderRepository;
import com.example.shopclothes.repositories.PaymentsRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.PaymentsServiceIPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentsService implements PaymentsServiceIPL {

    @Autowired
    private PaymentsRepo paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Boolean createPayment(PaymentRequestDto paymentRequestDto) {

        Order order = orderRepository.findById(paymentRequestDto.getOrderId()).orElse(null);
        Payments payment = new Payments();
        payment.setOrders(order);
        payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());
        payment.setPaymentDate(paymentRequestDto.getPaymentDate());
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setNote(paymentRequestDto.getNote());
        payment.setStatus(paymentRequestDto.getStatus());
        paymentRepository.save(payment);

        return true;
    }

    @Override
    public List<Payments> findByOrdersId(Long orderId) {
        return paymentRepository.findByOrdersId(orderId);
    }
}
