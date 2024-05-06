package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.MaterielRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.MaterielServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class MaterialService implements MaterielServices {

    @Autowired
    private MaterielRepo materielRepo;

    @Override
    public List<Material> getALL() {
        return materielRepo.getAll();
    }


    @Override
    public Page<Material> pageMaterial(Pageable pageable) {
        return materielRepo.findAll(pageable);
    }

    @Override
    public Material add(Material mt) {
        mt.setMaterialName(mt.getMaterialName());
        return materielRepo.save(mt);
    }

    @Override
    public Material detail(Long id) {
        return materielRepo.findById(id).orElse(null);
    }

    @Override
    public Material xoa(Long id) {
        Material mt = materielRepo.findById(id).orElse(null);
        if (mt != null) {
            mt.setDateCreate(mt.getDateCreate());
            mt.setDateUpdate(new Date());
            mt.setStatus(Status.NGUNG_HOAT_DONG);
            return materielRepo.save(mt);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }

//    @Override
//    public void save(Material object) {
//        materielRepo.save(object);
//    }
//
//    @Override
//    public void update(Material object, Long id) {
//        materielRepo.save(object);
//    }
//
//    @Override
//    public void delete(Long id) {
//        materielRepo.delete(id);
//    }
//
//    @Override
//    public void search(Long id) {
//        materielRepo.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Material> select() {
//        return materielRepo.findAll();
//    }
}
