package com.example.shopclothes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterOrderRequestDto {


    private String orderStatusName;
    private  String orderId;
    private  String orderType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer pageNo;
    private Integer pageSize;
}
