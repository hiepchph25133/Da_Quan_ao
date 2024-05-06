package com.example.shopclothes.service;

import com.example.shopclothes.dto.PaymentRequestDto;
import com.example.shopclothes.entity.Payments;

import java.util.List;

public interface PaymentsServiceIPL {

    public Boolean createPayment(PaymentRequestDto paymentRequestDto);

    public List<Payments> findByOrdersId(Long orderId);
}
