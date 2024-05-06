package com.example.shopclothes.controller;

import com.example.shopclothes.entity.CartDetail;
import com.example.shopclothes.service.impl.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CartDetail")

public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @GetMapping("/hien-thi")
    public List<CartDetail> hienThi(){
        return cartDetailService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        cartDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        cartDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(CartDetail cartDetail){
        cartDetailService.save(cartDetail);
    }

    @PostMapping("/update/{id}")
    public void update(CartDetail cartDetail, @PathVariable Long id){
        cartDetailService.update(cartDetail, id);
    }
}
