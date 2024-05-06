package com.example.shopclothes.dto;

import com.example.shopclothes.entity.propertis.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeatilsDTO {

    private Long id;
    private Date dateCreate;
    private BigDecimal price;
    private int quantity;
    private String code;
    private Status status;
    private String productName;
    private String categoryName;
    private String imageName;

//    private String sizeName;
//    private String colorName;
//    private String MateName;
}