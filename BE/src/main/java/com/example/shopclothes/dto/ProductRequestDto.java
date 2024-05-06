package com.example.shopclothes.dto;

import com.example.shopclothes.entity.propertis.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String productName;

    private String categoryName;

    private String MaterialName;

    private String producerName;

    private Boolean productHot;

    private Boolean productSale;

    private Boolean productNew;

    private String productDescribe;

    private Status status;
}
