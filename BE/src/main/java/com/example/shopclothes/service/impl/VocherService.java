package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.repositories.VocherRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VocherService implements IService<Vocher> {

    @Autowired
    private VocherRepo vocherRepo;

    @Override
    public void save(Vocher object) {
        vocherRepo.save(object);
    }

    @Override
    public void update(Vocher object, Long id) {
        vocherRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        vocherRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        vocherRepo.findById(id).orElse(null);
    }

    @Override
    public List<Vocher> select() {
        return vocherRepo.findAll();
    }
}
