package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.service.impl.VocherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Vocher")

public class VocherController {

    @Autowired
    private VocherService vocherService;

    @GetMapping("/hien-thi")
    public List<Vocher> hienThi(){
        return vocherService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        vocherService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        vocherService.search(id);
    }

    @PostMapping("/add")
    public void add(Vocher vocher){
        vocherService.save(vocher);
    }

    @PostMapping("/update/{id}")
    public void update(Vocher vocher, @PathVariable Long id){
        vocherService.update(vocher, id);
    }
}
