package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.BillDetail;
import com.example.shopclothes.repositories.BillDetailRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillDetailService implements IService<BillDetail> {

    @Autowired
    private BillDetailRepo billDetailRepo;

    @Override
    public void save(BillDetail object) {
        billDetailRepo.save(object);
    }

    @Override
    public void update(BillDetail object, Long id) {
        billDetailRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        billDetailRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        billDetailRepo.findById(id).orElse(null);
    }

    @Override
    public List<BillDetail> select() {
        return billDetailRepo.findAll();
    }
}
