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
public class VoucherFilterRequestDto {

    private String keyword;

    private LocalDateTime createdAtStart;

    private LocalDateTime createdAtEnd;

    private Integer status;

    private Integer pageNo;

    private Integer pageSize;
}
