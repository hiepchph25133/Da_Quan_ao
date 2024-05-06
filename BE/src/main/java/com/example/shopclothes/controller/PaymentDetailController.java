package com.example.shopclothes.controller;

import com.example.shopclothes.entity.PaymentsDetail;
import com.example.shopclothes.service.impl.PaymentsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PaymentDetail")

public class PaymentDetailController {

    @Autowired
    private PaymentsDetailService paymentsDetailService;

    @GetMapping("/hien-thi")
    public List<PaymentsDetail> hienThi(){
        return paymentsDetailService.select();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        paymentsDetailService.delete(id);
    }

    @GetMapping("/search/{id}")
    public void search(@PathVariable Long id){
        paymentsDetailService.search(id);
    }

    @PostMapping("/add")
    public void add(PaymentsDetail paymentsDetail){
        paymentsDetailService.save(paymentsDetail);
    }

    @PostMapping("/update/{id}")
    public void update(PaymentsDetail paymentsDetail, @PathVariable Long id){
        paymentsDetailService.update(paymentsDetail, id);
    }
}
