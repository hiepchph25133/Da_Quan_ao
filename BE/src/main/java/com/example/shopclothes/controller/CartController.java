package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Cart;
import com.example.shopclothes.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Cart")

public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/hien-thi")
    private List<Cart> hienThi(){
        return cartService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        cartService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        cartService.search(id);
    }

    @PostMapping("/add")
    public void add(Cart cart){
        cartService.save(cart);
    }

    @PostMapping("/update/{id}")
    public void update(Cart cart, @PathVariable Long id){
        cartService.update(cart, id);
    }
}
