package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.VocherDetail;
import com.example.shopclothes.repositories.VocherDetailRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VocherDetailService implements IService<VocherDetail> {

    @Autowired
    private VocherDetailRepo vocherDetailRepo;

    @Override
    public void save(VocherDetail object) {
        vocherDetailRepo.save(object);
    }

    @Override
    public void update(VocherDetail object, Long id) {
        vocherDetailRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        vocherDetailRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        vocherDetailRepo.findById(id).orElse(null);
    }

    @Override
    public List<VocherDetail> select() {
        return vocherDetailRepo.findAll();
    }
}
