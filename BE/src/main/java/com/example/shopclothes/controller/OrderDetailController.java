package com.example.shopclothes.controller;

import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.OrderDetailInStoreRequestDto;
import com.example.shopclothes.dto.OrderDetailResponseDto;
import com.example.shopclothes.dto.ResponseDto;
import com.example.shopclothes.dto.ResponseHandler;
import com.example.shopclothes.service.OrderDetailServiceIPL;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/orderDetails/")
@Tag(name = "OrderDetails",description = "( Rest API Hiển thị, thêm, sửa hóa đơn chi tiết )")
@Validated
public class OrderDetailController {

    @Autowired
    private OrderDetailServiceIPL orderDetailService;

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createOrderDetail(@RequestBody OrderDetailInStoreRequestDto orderDetailRequestDto) throws Exception {
        Boolean isCreated = orderDetailService.createOrderDetail(orderDetailRequestDto);
        if (isCreated){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(NotificationConstants.STATUS_201,NotificationConstants.MESSAGE_201));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }

    @GetMapping("getOrderDetailByOrderId")
    public ResponseEntity<?> getOrderDetailByOrderId(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam Long orderId) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<OrderDetailResponseDto> orderDetailPage = orderDetailService.getOrderDetailByOrderId(orderId,pageable);
        List<OrderDetailResponseDto> orderDetailList = orderDetailPage.getContent();
        return ResponseHandler.generateResponse(HttpStatus.OK,orderDetailList,orderDetailPage);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> deleteSize(@RequestParam Long id){
        Boolean isDeleted = orderDetailService.deleteOrderDetail(id);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200,NotificationConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }

    @PatchMapping ("updateQuantityOrderDetail")
    public ResponseEntity<ResponseDto> updateOrderDetail(@RequestParam Integer quantity, @RequestParam Long id){
        Boolean isUpdated = orderDetailService.updateQuantityOrderDetail(quantity,id);
        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200,NotificationConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }
}
