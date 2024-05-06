package com.example.shopclothes.service;

import com.example.shopclothes.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryServices {

    public Page<Category> pageColor(Pageable pageable);

    public List<Category> select();

    public Category add(Category ct);

    public Category detail(Long id);

    public Category xoa(Long id);

    public List<Category> findAllByDeletedTrue();
}
