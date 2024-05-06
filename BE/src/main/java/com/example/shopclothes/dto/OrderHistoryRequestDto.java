package com.example.shopclothes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryRequestDto {

    private String note;

    private Long statusId;

    private Long orderId;
}
