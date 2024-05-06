package com.example.shopclothes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailRequestDto {

    private Long productId;

    private String colorName;

    private String sizeName;

    private String materialName;

    private Integer quantity;

    private Double price;
}
