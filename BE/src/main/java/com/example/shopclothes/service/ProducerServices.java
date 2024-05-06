package com.example.shopclothes.service;

import com.example.shopclothes.entity.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ProducerServices {

    public List<Producer> getAllNSX();

    public Page<Producer> pageNSX(Pageable pageable);

    public Page<Producer> pageSearchNSX(String key,Integer status, Pageable pageable);

    public Producer add(Producer nhaSanXuat);

    public Producer detail(Long id);

    public Producer xoa( Long id);


    public List<Producer> findByDeletedTrue();
//    List<Producer> findByNhaSanXuatString (List<String> nsxString) ;

//    Page<Producer> getSuppliers(String name, String status, Date dateCreate, Date dateUpdate, Pageable pageable);
}
