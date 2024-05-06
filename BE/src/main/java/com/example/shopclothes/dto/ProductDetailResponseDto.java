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
public class ProductDetailResponseDto {
    private Long id;

    private String productName;

    private String productAvatar;

    private String colorName;

    private String sizeName;

    private String materialName;

    private Integer quantity;

    private Double price;


    private Status status;
}
