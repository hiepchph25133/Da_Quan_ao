package com.example.shopclothes.controller;

import com.example.shopclothes.entity.BillHistory;
import com.example.shopclothes.repositories.BillHistoryRepo;
import com.example.shopclothes.service.impl.BillHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BillHistory")

public class BillHistoryController {

    @Autowired
    private BillHistoryService billHistoryService;
    @Autowired
    private BillHistoryRepo billHistoryRepo;

    @GetMapping("/hien-thi")
    public List<BillHistory> hienThi(){
        return billHistoryService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        billHistoryService.delete(id);
    }

    @GetMapping("/seaech/{id}")
    public void search(@PathVariable Long id){
        billHistoryService.search(id);
    }

    @PostMapping("/add")
    public void add(BillHistory billHistory){
        billHistoryRepo.save(billHistory);
    }

    @PostMapping("/update/{id}")
    public void update(BillHistory billHistory, @PathVariable Long id){
        billHistoryService.update(billHistory, id);
    }
}
