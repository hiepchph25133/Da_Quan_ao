package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Return;
import com.example.shopclothes.repositories.ReturnRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReturnService implements IService<Return> {

    @Autowired
    private ReturnRepo returnRepo;

    @Override
    public void save(Return object) {
        returnRepo.save(object);
    }

    @Override
    public void update(Return object, Long id) {
        returnRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        returnRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        returnRepo.findById(id).orElse(null);
    }

    @Override
    public List<Return> select() {
        return returnRepo.findAll();
    }
}
