package com.example.shopclothes.controller;

import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.*;
import com.example.shopclothes.entity.Product;
import com.example.shopclothes.service.impl.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "( Rest API Hiển thị, thêm, sửa, xóa, tìm kiếm, phân trang sản phẩm )")

public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("findAllByDeletedTrue")
    public ResponseEntity<List<Product>> findAllByDeletedTrue() {
        List<Product> productList = productService.finByName();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productList);
    }



    @PostMapping("getAllProducts")
    public ResponseEntity<?> getProducts(@RequestBody ProductDetailFilterRequestDto requestDto, Pageable pageable) {
        Page<ProductFilterResponseDto> productPage = productService.findProductsAdminByFilters(requestDto, pageable);
        List<ProductFilterResponseDto> productResponseDtoList = productPage.getContent();
        return ResponseHandler.generateResponse(HttpStatus.OK, productResponseDtoList, productPage);
    }

    @PostMapping("create")
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequestDto productRequestDto) {
        System.out.println("CategoryName in create method: " + productRequestDto.getProductName());
        Product  product = productService.createProduct(productRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody ProductRequestDto productRequestDto, @RequestParam Long id) {
        Boolean isUpdated = productService.updateProduct(productRequestDto, id);
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

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam Long id) {
        Boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200, NotificationConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }


}
