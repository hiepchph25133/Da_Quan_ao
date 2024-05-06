package com.example.shopclothes.service.impl;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ProducerRepo;

import com.example.shopclothes.service.ProducerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ProducerServiceIPL implements ProducerServices {

//    @Autowired
//    private ProducerRepo producerRepo;
//
//    @Override
//    public void save(Producer object) {
//        producerRepo.save(object);
//    }
//
//    @Override
//    public void update(Producer object, Long id) {
//        producerRepo.save(object);
//    }
//
////    @Override
////    public void delete(Long id) {
////        producerRepo.delete(id);
////    }
//
//
//    //    @Override
//    public void xoa(Long id) {
//        Producer nhaSanXuat = producerRepo.findById(id).orElse(null);
//        nhaSanXuat.setName(nhaSanXuat.getCode());
//        nhaSanXuat.setDateCreate(nhaSanXuat.getDateCreate());
//        nhaSanXuat.setDateUpdate(new Date());
//        nhaSanXuat.setStatus(Status.NGUNG_HOAT_DONG);
//        producerRepo.save(nhaSanXuat);
//    }
//    @Override
//    public void search(Long id) {
//
//    }
//
////    @Override
////    public Producer search(Long id) {
////       return producerRepo.findById(id).orElse(null);
////    }
//
//    @Override
//    public List<Producer> select() {
//        return producerRepo.getAll();
//    }
//
////    @Override
//    public Producer detail(Long id) {
//        return producerRepo.findById(id).orElse(null);
//    }
//
////    @Override
//    public Producer add(Producer nhaSanXuat) {
//        nhaSanXuat.setName(nhaSanXuat.getName());
//        return producerRepo.save(nhaSanXuat);
//    }


    @Autowired
    private ProducerRepo producerRepo;

    @Override
    public List<Producer> getAllNSX() {
        return producerRepo.getAll();
    }

    @Override
    public Page<Producer> pageNSX(Pageable pageable) {
        return producerRepo.findAll(pageable);
    }

    @Override
    public Page<Producer> pageSearchNSX(String key, Integer trangThai,  Pageable pageable) {
        return producerRepo.searchPageNSX(key,trangThai ,pageable);
    }

    @Override
    public Producer add(Producer nhaSanXuat) {
        nhaSanXuat.setProducerName(nhaSanXuat.getProducerName());
        return producerRepo.save(nhaSanXuat);
    }

//    @Override
//    public Producer add(Producer nhaSanXuat) {
//        // Kiểm tra nếu nhà sản xuất đã tồn tại
//        Optional<Producer> existingProducer = producerRepo.findById(nhaSanXuat.getId());
//        if (existingProducer.isPresent()) {
//            Producer existing = existingProducer.get();
//            existing.setName(nhaSanXuat.getName());
//
//            // Thực hiện cập nhật
//            return producerRepo.save(existing);
//        } else {
//            // Nếu nhà sản xuất không tồn tại, thực hiện thêm mới
//            return producerRepo.save(nhaSanXuat);
//        }
//    }
    @Override
    public Producer detail(Long id) {
        return producerRepo.findById(id).orElse(null);
    }

    @Override
    public Producer xoa(Long id) {
        Producer nhaSanXuat = producerRepo.findById(id).orElse(null);
        if (nhaSanXuat != null) {
            nhaSanXuat.setDateCreate(nhaSanXuat.getDateCreate());
            nhaSanXuat.setDateUpdate(new Date());
            nhaSanXuat.setStatus(Status.NGUNG_HOAT_DONG);
            return producerRepo.save(nhaSanXuat);
        } else {
            return null; // Hoặc giá trị tương tự để biểu thị không có gì thay đổi.
        }
    }

    @Override
    public List<Producer> findByDeletedTrue() {
        return producerRepo.findByDeletedTrue();
    }

}
