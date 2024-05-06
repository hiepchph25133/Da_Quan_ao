package com.example.shopclothes.controller;

import com.example.shopclothes.entity.BillDetail;
import com.example.shopclothes.service.impl.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BillDetail")

public class BillDetailController {

    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("/hien-thi")
    public List<BillDetail> hienThi(){
        return billDetailService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        billDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void Search(@PathVariable Long id){
        billDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(BillDetail billDetail){
        billDetailService.save(billDetail);
    }

    @PostMapping("/update/{id}")
    public void update(BillDetail billDetail, @PathVariable Long id){
        billDetailService.update(billDetail, id);
    }
}
