package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.PaymentsDetail;
import com.example.shopclothes.repositories.PaymentDetailRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentsDetailService implements IService<PaymentsDetail> {

    @Autowired
    private PaymentDetailRepo paymentDetailRepo;

    @Override
    public void save(PaymentsDetail object) {
        paymentDetailRepo.save(object);
    }

    @Override
    public void update(PaymentsDetail object, Long id) {
        paymentDetailRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        paymentDetailRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        paymentDetailRepo.findById(id).orElse(null);
    }

    @Override
    public List<PaymentsDetail> select() {
        return paymentDetailRepo.findAll();
    }
}
