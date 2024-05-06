package com.example.shopclothes.service;

import com.example.shopclothes.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaterielServices {

    public List<Material> getALL();

    public Page<Material> pageMaterial(Pageable pageable);

    public Material add(Material mt);

    public Material detail(Long id);

    public Material xoa(Long id);
}
