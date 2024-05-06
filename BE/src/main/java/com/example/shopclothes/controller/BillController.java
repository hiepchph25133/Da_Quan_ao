package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Bill;
import com.example.shopclothes.service.impl.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Bill")

public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/hien-thi")
    public List<Bill> hienThi(){
        return billService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        billService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        billService.search(id);
    }

    @PostMapping("/add")
    public void add(Bill bill){
        billService.save(bill);
    }

    @PostMapping("/update/{id}")
    public void update(Bill bill, @PathVariable Long id){
        billService.update(bill, id);
    }
}
