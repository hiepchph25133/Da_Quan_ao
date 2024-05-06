package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.CategoryRepo;
import com.example.shopclothes.service.CategoryServices;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class CategoryService implements CategoryServices {

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public Page<Category> pageColor(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

        @Override
    public List<Category> select() {
        return categoryRepo.findAll();
    }


    @Override
    public Category add(Category ct) {
        ct.setCategoryName(ct.getCategoryName());
        return categoryRepo.save(ct);
    }

    @Override
    public Category detail(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category xoa(Long id) {
        Category ct = categoryRepo.findById(id).orElse(null);
        if (ct != null) {
            ct.setDateCreate(ct.getDateCreate());
            ct.setDateUpdate(new Date());
            ct.setStatus(Status.NGUNG_HOAT_DONG);
            return categoryRepo.save(ct);
        } else {
            return null;
        }
    }


    @Override
    public List<Category> findAllByDeletedTrue() {
        return categoryRepo.findByStatus();
    }

}
