package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ColorRepo;
import com.example.shopclothes.service.ColorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class ColorService implements ColorServices {

    @Autowired
    private ColorRepo colorRepo;



    @Override
    public List<Color> getALLColor() {
        return colorRepo.getAll();
    }


    @Override
    public Page<Color> pageColor(Pageable pageable) {
        return colorRepo.findAll(pageable);
    }


    @Override
    public Color add(Color nhaSanXuat) {
        nhaSanXuat.setColorName(nhaSanXuat.getColorName());
        return colorRepo.save(nhaSanXuat);
    }

    @Override
    public Color detail(Long id) {
        return colorRepo.findById(id).orElse(null);
    }

    @Override
    public Color xoa(Long id) {
        Color MS = colorRepo.findById(id).orElse(null);
        if (MS != null) {
            MS.setDateCreate(MS.getDateCreate());
            MS.setDateUpdate(new Date());
            MS.setStatus(Status.NGUNG_HOAT_DONG);
            return colorRepo.save(MS);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }



}
