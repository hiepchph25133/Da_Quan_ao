package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Bill;
import com.example.shopclothes.repositories.BillRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillService implements IService<Bill> {

    @Autowired
    private BillRepo billRepo;

    @Override
    public void save(Bill object) {
        billRepo.save(object);
    }

    @Override
    public void update(Bill object, Long id) {
        billRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        billRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        billRepo.findById(id).orElse(null);
    }

    @Override
    public List<Bill> select() {
        return billRepo.findAll();
    }
}
