package com.example.shopclothes.controller;


import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.OrderHistoryRequestDto;
import com.example.shopclothes.dto.ResponseDto;
import com.example.shopclothes.entity.OrderHistory;
import com.example.shopclothes.service.OrderHistoryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/timeline/")
@Tag(name = "OrderHistory", description = "( Rest API Hiển thị lịch sử hóa đơn )")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryServiceImpl orderHistoryService;

    @GetMapping("findAllTimelineByOrderId")
    public ResponseEntity<?> getAllTimelineByOrderId(@RequestParam Long id){
        List<OrderHistory> response = orderHistoryService.findAllTimeLinesByOrderId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("findByOrderIdAndStatus")
    public ResponseEntity<?> findByOrderIdAndStatus(@RequestParam Long id){
        List<OrderHistory> response = orderHistoryService.findByOrderIdAndStatus(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createTimeLine(@Valid @RequestBody OrderHistoryRequestDto timeLineRequestDto) {
        Boolean isCreated = orderHistoryService.createTimeLine(timeLineRequestDto);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(NotificationConstants.STATUS_201, NotificationConstants.MESSAGE_201));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }


}
