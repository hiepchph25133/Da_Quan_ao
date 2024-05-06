package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Return;
import com.example.shopclothes.service.impl.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Return")

public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @GetMapping("/hien-thi")
    public List<Return> hienThi(){
        return returnService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        returnService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        returnService.search(id);
    }

    @PostMapping("/add")
    public void add(Return aReturn){
        returnService.save(aReturn);
    }

    @PostMapping("/update/{id}")
    public void update(Return aReturn, @PathVariable Long id){
        returnService.update(aReturn,id);
    }
}
