package com.example.shopclothes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequestDto {

    private Long orderId;
    private String newStatusName;
    private String note;
}
