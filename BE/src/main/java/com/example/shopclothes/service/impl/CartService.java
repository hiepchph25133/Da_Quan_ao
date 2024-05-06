package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Cart;
import com.example.shopclothes.repositories.CartRepo;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CartService implements IService<Cart> {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public void save(Cart object) {
        cartRepo.save(object);
    }

    @Override
    public void update(Cart object, Long id) {
        cartRepo.save(object);
    }

    @Override
    public void delete(Long id) {
        cartRepo.delete(id);
    }

    @Override
    public void search(Long id) {
        cartRepo.findById(id).orElse(null);
    }

    @Override
    public List<Cart> select() {
        return cartRepo.findAll();
    }
}
