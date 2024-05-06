package com.example.shopclothes.controller;

import com.example.shopclothes.entity.VocherDetail;
import com.example.shopclothes.service.impl.VocherDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/VocherDetail")

public class VocherDetailController {

    @Autowired
    private VocherDetailService vocherDetailService;

    @GetMapping("/hien-thi")
    public List<VocherDetail> hienThi(){
        return vocherDetailService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        vocherDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        vocherDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(VocherDetail vocherDetail){
        vocherDetailService.save(vocherDetail);
    }

    @PostMapping("/update/{id}")
    public void update(VocherDetail vocherDetail, @PathVariable Long id){
        vocherDetailService.update(vocherDetail, id);
    }
}
