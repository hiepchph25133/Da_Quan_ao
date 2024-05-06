package com.example.shopclothes.dto;

import com.example.shopclothes.entity.propertis.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterResponseDto {

    private Long id;
    private String productImage;
    private String productName;
    private String categoryName;
    private String supplierName;
    private String productDescribe;
    private Long quantityTotal;
    private Double price;
    private Status status;
    private LocalDateTime createdAt; // Thêm một tham số LocalDateTime
    private LocalDateTime createdBy;


}
