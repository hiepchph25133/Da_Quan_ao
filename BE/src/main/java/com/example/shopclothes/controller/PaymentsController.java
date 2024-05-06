package com.example.shopclothes.controller;


import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.PaymentRequestDto;
import com.example.shopclothes.dto.ResponseDto;
import com.example.shopclothes.entity.Payments;
import com.example.shopclothes.service.PaymentsServiceIPL;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/payments/")
@Tag(name = "Payments", description = "( Rest API Hiển thị, thêm, sửa, xóa phương thức thanh toán )")
public class PaymentsController {

    @Autowired
    private PaymentsServiceIPL paymentService;

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        Boolean isCreated = paymentService.createPayment(paymentRequestDto);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(NotificationConstants.STATUS_201, NotificationConstants.MESSAGE_201));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }

    @GetMapping("getAllPaymentByOrdersId")
    public ResponseEntity<List<Payments>> getAllPaymentByOrdersId(@RequestParam(required = false) Long orderId) {

        List<Payments> paymentList = paymentService.findByOrdersId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(paymentList);
    }
}
