package com.example.shopclothes.service;

import com.example.shopclothes.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface sizeSevices {

    public List<Size> getAllSize();

    public Page<Size> pageColor(Pageable pageable);

    public Size add(Size size);

    public Size detail(Long id);

    public Size xoa(Long id);
}
