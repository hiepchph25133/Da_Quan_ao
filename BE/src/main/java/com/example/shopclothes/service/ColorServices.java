package com.example.shopclothes.service;

import com.example.shopclothes.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ColorServices {

    public List<Color> getALLColor() ;

    public Page<Color> pageColor(Pageable pageable);

    public Color add(Color nhaSanXuat);

    public Color detail(Long id);

    public Color xoa(Long id);
}
