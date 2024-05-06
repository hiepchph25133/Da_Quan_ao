package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Size;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.SizeRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.sizeSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class SizeService implements sizeSevices {

    @Autowired
    private SizeRepo sizeRepo;

    @Override
    public List<Size> getAllSize() {
        return sizeRepo.getAll();
    }


    @Override
    public Page<Size> pageColor(Pageable pageable) {
        return sizeRepo.findAll(pageable);
    }


    @Override
    public Size add(Size size) {
        size.setSizeName(size.getSizeName());
        return sizeRepo.save(size);
    }

    @Override
    public Size detail(Long id) {
        return sizeRepo.findById(id).orElse(null);
    }


    @Override
    public Size xoa(Long id) {
        Size size = sizeRepo.findById(id).orElse(null);
        if (size != null) {
            size.setDateCreate(size.getDateCreate());
            size.setDateUpdate(new Date());
            size.setStatus(Status.NGUNG_HOAT_DONG);
            return sizeRepo.save(size);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }

}
