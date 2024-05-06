//package com.example.shopclothes.controller;
//
//import com.example.shopclothes.entity.Account;
//import com.example.shopclothes.service.impl.AccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/Account")
//
//public class AccountController {
//
//    @Autowired
//    private AccountService accountService;
//
//    @GetMapping("/hien-thi")
//    public List<Account> hienThi(){
//        return accountService.select();
//    }
//
//    @GetMapping("/delete/{id}")
//    public void delete(@PathVariable Long id){
//        accountService.delete(id);
//    }
//
//    @GetMapping("/search/{id}")
//    public void search(@PathVariable Long id){
//        accountService.search(id);
//    }
//
//    @PostMapping("/add")
//    public  void add(Account account){
//        accountService.save(account);
//    }
//
//    @PostMapping("/update/{id}")
//    public void update(Account account, Long id){
//        accountService.update(account, id);
//    }
//}
