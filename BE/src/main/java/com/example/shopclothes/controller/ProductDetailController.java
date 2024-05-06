package com.example.shopclothes.controller;

import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.*;
import com.example.shopclothes.entity.Product;
import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ProductDetailRepo;
import com.example.shopclothes.repositories.ProductRepo;
import com.example.shopclothes.service.ProductdetailsServices;
import com.example.shopclothes.service.impl.ProductDetailService;
import com.example.shopclothes.service.impl.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ProductDetail")
@Tag(name = "ProductDetail", description = "( Rest API Hiển thị, thêm, sửa, xóa mau sac )")
@Validated
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

//    @Autowired
//    private ProductdetailsServices productdetailsServices;

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductService productService;



    @PostMapping("/createList")
    public ResponseEntity<?> createProductDetails(@RequestBody List<ProductDetailRequestDto> productDetailRequestDtos) {
        Boolean isCreated = productDetailService.createProductDetails(productDetailRequestDtos);
        if (isCreated){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(NotificationConstants.STATUS_201,NotificationConstants.MESSAGE_201));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProductDetail(@RequestBody ProductDetailRequestDto productRequestDto, @RequestParam Long id) {
        Boolean isUpdated = productDetailService.updateProductDetail(productRequestDto, id);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200, NotificationConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }

    @GetMapping("findProductById")
    public ResponseEntity<Product> findProductById(@RequestParam Long productId) {
        Product product = productService.findProductById(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @GetMapping("getAllByProductId")
    public ResponseEntity<?> findAllByProductId(@RequestParam Long productId,
                                                @RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<ProductDetailResponseDto> responseDtoPage = productDetailService.findAllByProductId(productId, pageable);
        List<ProductDetailResponseDto> detailResponseDtoList = responseDtoPage.getContent();
        return ResponseHandler.generateResponse(
                HttpStatus.OK
                , detailResponseDtoList
                , responseDtoPage);
    }

    @PostMapping("getAllProductDetailsFilter")
    public ResponseEntity<?> getProductDetails(@RequestBody ProductDetailFilterRequestDto requestDto) {

        Page<ProductDetailResponseDto> productPage = productDetailService.getProductDetails(requestDto);

        List<ProductDetailResponseDto> productResponseDtoList = productPage.getContent();
        return ResponseHandler.generateResponse(
                HttpStatus.OK
                , productResponseDtoList
                , productPage);
    }
}
