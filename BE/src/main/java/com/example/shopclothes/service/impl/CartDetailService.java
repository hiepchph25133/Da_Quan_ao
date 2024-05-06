package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.CartDetail;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CartDetailService implements IService<CartDetail> {

    @Autowired
    private com.example.shopclothes.repositories.CartDetailRepo cartDetailRepo;

    @Override
    public void save(CartDetail object) {
        cartDetailRepo.save(object);
    }

    @Override
    public void update(CartDetail object, Long id) {
        cartDetailRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        cartDetailRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        cartDetailRepo.findById(id).orElse(null);
    }

    @Override
    public List<CartDetail> select() {
        return cartDetailRepo.findAll();
    }
}
