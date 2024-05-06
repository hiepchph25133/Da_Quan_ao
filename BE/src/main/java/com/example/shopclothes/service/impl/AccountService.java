//package com.example.shopclothes.service.impl;
//
//import com.example.shopclothes.entity.Account;
//import com.example.shopclothes.repositories.AccountRepo;
//import com.example.shopclothes.service.IService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//
//public class AccountService implements IService<Account> {
//
//    @Autowired
//    private AccountRepo accountRepo;
//
//    @Override
//    public void save(Account object) {
//        accountRepo.save(object);
//    }
//
//    @Override
//    public void update(Account object, Long id) {
//        accountRepo.save(object);
//    }
//
//    @Override
//    public void delete(Long id) {
//        accountRepo.delete(id);
//    }
//
//    @Override
//    public void search(Long id) {
//        accountRepo.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Account> select() {
//        return accountRepo.findAll();
//    }
//}
