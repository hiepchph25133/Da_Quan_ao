package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.BillHistory;
import com.example.shopclothes.repositories.BillHistoryRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BillHistoryService implements IService<BillHistory> {

    @Autowired
    private BillHistoryRepo billHistoryRepo;

    @Override
    public void save(BillHistory object) {
        billHistoryRepo.save(object);
    }

    @Override
    public void update(BillHistory object, Long id) {
        billHistoryRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        billHistoryRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        billHistoryRepo.findById(id).orElse(null);
    }

    @Override
    public List<BillHistory> select() {
        return billHistoryRepo.findAll();
    }
}
